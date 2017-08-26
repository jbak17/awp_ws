/*
How can be represent a location on a chess board in JS? We have rows and columns.
There are 64 squares.

If we use in integer representation. 1 = A8 64 = H1

Square_num % 8 = column, square_num / 8 = row

We can go back the other way so if we want to place
a piece on the board we can do:
    row*8 + column

[1][2][3][4][5][6][7][8]
[9][10][11][12][13][14][15][16]
[17][18][19][20][21][22][23][24]
...

It would be better to build it one row at a time with sequential
row id, followed by square id. That way we could look up the
row and the square.

This is just for the front end, so the data is actually all
kept on the server.


/app {stores all state}
    |
    |
  /board
    |
  /row
    |
  /square



 */

function Square(props) {
    const img = (props.value) ? props.value.symbol : "";
    return (
        <button className={props.color} onClick={props.onClick} value={img}></button>
    );
}

class Row extends React.Component{

    renderSquare(i) {
        let color;
        ((i + this.props.color ) % 2 === 0) ? color = "wsquare" : color = "bsquare";

        //console.log(color);
        return (
            <Square
                color={color}
                column={i+1} //Column value in chess notation
                value={this.props.squares[i]}
                onClick={() => this.props.onClick(i)}
            />
        );
    }

    render() {
        let squares = [];
        for (let i=0; i < 8; i++) {
            squares.push(this.renderSquare(i));
        }
        return <tr>{squares}</tr>;
    }
}

class Board extends React.Component {

    /*
    We only want to pass through the relevant squares to each section.
    0-7, 8-15, 16-23, 24-31, 32-39, 40-47, 48-55, 56-63
    As code slice(8*i, (8*(i+1)-1)
     */
    render() {
        let columns = [];
        for (let i = 0; i<8; i++){
            columns.push(<Row rowID={8-i} squares={this.props.squares.slice(8*i, (8*(i+1)-1))} onClick={i => this.handleClick(i)} color={i%2}/>)
        }
        return (
            <table>{columns}</table>
        );
    }
}

class Game extends React.Component {
    constructor(props) {
        super();
        this.state = {
            history: [{
                    squares: new Array(64).fill(null),
                    pieces: JSON.parse(props.startBoard),
            }],
        };
    }

    /*
    Sets the value of each square to its initial state
    as passed in through constructor.
     */
    update(pieces){
        let board = new Array(64).fill(null);
        pieces.forEach(e => {
            const location = e.location.square;
            board[location-1] = e;
        });
        return board
    }

    handleClick(i) {}

    render() {
        //this.update(this.state.history.pieces);
        const history = this.state.history[0];
        let current = this.update(history.pieces);
        console.log(current);
        return (
            <div className="game">
                <div className="game-board">
                    <Board
                        squares={current}
                        onClick={i => this.handleClick(i)}
                    />
                </div>
            </div>
        );
    }
}

// ========================================
let root = document.getElementById('reactRoot');
let game_data = root.getAttribute('game');

ReactDOM.render(<Game startBoard={game_data}/>, root);
