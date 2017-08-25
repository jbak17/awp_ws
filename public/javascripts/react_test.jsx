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
    return (
        <button className={props.color} onClick={props.onClick}>
            <span>{props.value}</span>
        </button>
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

    render() {
        let columns = [];
        for (let i = 0; i<8; i++){
            columns.push(<Row rowID={8-i} squares={this.props.squares} onClick={i => this.handleClick(i)} color={i%2}/>)
        }
        return (
            <table>{columns}</table>
        );
    }
}

class Game extends React.Component {
    constructor() {
        super();
        this.state = {
            history: [
                {
                    squares: Array(64).fill(null)
                }
            ],
        };
    }

    handleClick(i) {}

    render() {
        const history = this.state.history;
        const current = history[0];

        const moves = history.map((step, move) => {
            const desc = move ? "Move #" + move : "Game start";
            return (
                <li key={move}>
                    <a href="#" onClick={() => this.jumpTo(move)}>{desc}</a>
                </li>
            );
        });

        return (
            <div className="game">
                <div className="game-board">
                    <Board
                        squares={current.squares}
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

ReactDOM.render(<Game data={game_data}/>, root);
