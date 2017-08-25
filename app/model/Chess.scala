package model

import services._

case class Id[T](id: String)

object Chess {

  case class Piece(
      player:Player,
      position:Square,
      kind:String
  )

  case class GameState(pieces:Seq[ChessPiece])
'a
  def newGame():GameState = {
    GameState(
      pieces = creatInitialBoard.toSeq,
      )
  }

  /*Create a starting board with a full complement of
black and white pieces
*/
  def creatInitialBoard(): List[ChessPiece] = {

    def createPawns(): List[Pawn] = {
      val r = List(1,2,3,4,5,6,7,8)
      val blPawns: List[Pawn] = r.map(p => Pawn('b', Square(7, p)))
      val whPawns: List[Pawn] = r.map(p => Pawn('w', Square(2, p)))
      whPawns ::: blPawns
    }
    def createRooks(): List[Rook] = {
      val nums = List(1, 8)

      val wrks = nums.map(x =>  Rook('w', Square(1, x)))
      val brks = nums.map(x =>  Rook('b', Square(8, x)))

      wrks ::: brks
    }
    def createKnights(): List[Knight] = {
      val nums = List(2, 7)

      val wrks = nums.map(x =>  Knight('w', Square(1, x)))
      val brks = nums.map(x =>  Knight('b', Square(8, x)))

      wrks ::: brks
    }
    def createBishops(): List[Bishop] = {
      val nums = List(3, 5)

      val wrks = nums.map(x =>  Bishop('w', Square(1, x)))
      val brks = nums.map(x =>  Bishop('b', Square(8, x)))

      wrks ::: brks
    }
    def createQueens(): List[Queen] = {

      val white =  Queen('w', Square(1, 4))
      val black =  Queen('b', Square(8, 4))

      List(white, black)
    }
    def createKings(): List[King] = {

      val white =  King('w', Square(1, 5))
      val black =  King('b', Square(8, 5))

      List(white, black)
    }

    var pieceList: List[ChessPiece] = List()
    List(createPawns(), createRooks(), createKings(), createBishops(), createQueens(), createKnights()).foldLeft(pieceList)(_ ::: _)

  }




}
