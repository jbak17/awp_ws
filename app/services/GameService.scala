package services

import javax.inject.Singleton

import model._

import scala.collection.JavaConverters._
import scala.util.Random

@Singleton
class GameService {

  /** For the moment, IDs are just 10-digit random strings */
  def allocateId:Id[Game] = Id(Random.alphanumeric.take(10).mkString);

  // keep a list of the games in memory because
  // I don't yet have a database
  import java.util.concurrent.ConcurrentHashMap

  /**
    * This is a Java concurrent hashmap, but "asScala" (ie, with a Scala wrapper around it to give it methods that are
    * idiomatic for Scala code).
    */
  val activeGames = new ConcurrentHashMap[Id[Game], Game]().asScala

  /** Creates a new game with no players and a random ID */
  def newGame(p:Player):Game = {
    val g = Game(id = allocateId, players = Seq(p), gameState = Chess.newGame())
    activeGames(g.id) = g
    g
  }

  def joinGame(p:Player, gameId:Id[Game]) = {
    if (activeGames.contains(gameId)) {
      activeGames(gameId) = activeGames(gameId).withPlayer(p)
    }
  }

  def getGame(gameId:Id[Game]) = activeGames.get(gameId)


  def allGames:Seq[Game] = activeGames.values.toSeq

}
