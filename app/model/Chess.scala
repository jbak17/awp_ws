package model

case class Id[T](id: String)

object Chess {

  case class Square(x: Int, y: Int)

  case class Piece(
      player:Player,
      position:Square,
      kind:String
  )

  case class GameState(pieces:Seq[Piece])
'a
  def newGame():GameState = {
    GameState(
      pieces = Seq.empty,
      )
  }


}
