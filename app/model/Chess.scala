package model

import play.api.libs.json.{JsValue, Json, Writes}
import services._

case class Id[T](id: String)

object Chess {

  implicit val pieceWrites = new Writes[JsonPiece] {

    def writes(p: JsonPiece) = {
      Json.obj(
        "location" -> p.position,
        "colour" -> p.colour,
        "symbol" -> p.representation,
        "type" -> p.kind,
      )
    }
  }

  case class JsonPiece(
    colour:String,
    position:Square,
    kind:String,
    representation: String
  )

  /*todo We need to get back to a gamestate in order to
  use the chess api. So we need to go from Json from the
  server -> Seq[ChessPiece]. From the server we want
  all the pieces.

   */

  case class GameState(pieces:Seq[ChessPiece])

  def newGame():GameState = {
    GameState(
      pieces = creatInitialBoard.toSeq,
      )
  }

  /*Create a starting board with a full complement of
black and white pieces
*/
  def creatInitialBoard(): List[ChessPiece] = {
    val black = "b"
    val white = "w"

    def createPawns(): List[Pawn] = {
      val r = List(1,2,3,4,5,6,7,8)
      val blPawns: List[Pawn] = r.map(p => Pawn(black, Square(7, p)))
      val whPawns: List[Pawn] = r.map(p => Pawn(white, Square(2, p)))
      whPawns ::: blPawns
    }
    def createRooks(): List[Rook] = {
      val nums = List(1, 8)

      val wrks = nums.map(x =>  Rook(white, Square(1, x)))
      val brks = nums.map(x =>  Rook(black, Square(8, x)))

      wrks ::: brks
    }
    def createKnights(): List[Knight] = {
      val nums = List(2, 7)

      val wrks = nums.map(x =>  Knight(white, Square(1, x)))
      val brks = nums.map(x =>  Knight(black, Square(8, x)))

      wrks ::: brks
    }
    def createBishops(): List[Bishop] = {
      val nums = List(3, 6)

      val wrks = nums.map(x =>  Bishop(white, Square(1, x)))
      val brks = nums.map(x =>  Bishop(black, Square(8, x)))

      wrks ::: brks
    }
    def createQueens(): List[Queen] = {

      val w =  Queen(white, Square(1, 4))
      val b =  Queen(black, Square(8, 4))

      List(w, b)
    }
    def createKings(): List[King] = {

      val w =  King(white, Square(1, 5))
      val b =  King(black, Square(8, 5))

      List(w, b)
    }

    var pieceList: List[ChessPiece] = List()
    List(createPawns(), createRooks(), createKings(), createBishops(), createQueens(), createKnights()).foldLeft(pieceList)(_ ::: _)

  }

  def gameStateToJson(game: GameState): JsValue = {
    val jsonPieces = game.pieces.map(x => ChessPiece.toJsonPiece(x))
    Json.toJson(jsonPieces)
  }




}
