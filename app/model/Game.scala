package model

import scala.util.Random

object Player {

  def randomName = names(Random.nextInt(names.length))

  val names:Seq[String] = Seq("Algernon", "Bertie", "Cecily", "Dahlia", "Earnest")


}


case class Player(ip:String, name:String = Player.randomName)

/**
  * A game. The data in this is immutable -- as the data is eventually going to be saved in the database, and is only
  * going to be in memory briefly during requests, there's not much need for it to be mutable. Instead, we can just
  * have "mutations", such as calls to add players, return a clone of the object but with the new player added.
  */
case class Game(
  id:Id[Game],
  players:Seq[Player],
  gameState:Chess.GameState
) {

  /** Returns a new Game object with the player added into players */
  def withPlayer(p:Player) = this.copy(players = players :+ p)


}