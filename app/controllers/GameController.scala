package controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc._
import services.GameService
import model.{Chess, Game, Player, Id}

@Singleton
class GameController @Inject()(cc: ControllerComponents,
                               gameService: GameService) extends AbstractController(cc) {


  def index = Action { Ok(views.html.index(gameService.allGames)) }

  def newGame = Action { request:Request[AnyContent] =>

    val ip = request.toString
    val p = Player(ip = ip)
    val game = gameService.newGame(p)

    Redirect(routes.GameController.index())
  }

  def playGame(id: String) = Action { request:Request[AnyContent] =>
    val gid: Id[Game] = Id(id)
    val game: Option[Game] = gameService.getGame(gid)
    game match {
      case Some(g) =>
        Ok(views.html.game(g))
      case None =>
        Ok(views.html.index(gameService.allGames))
    }



  }

  def joinGame(id:String) = Action { request:Request[AnyContent] =>

    val ip = request.toString
    val p = Player(ip = ip)
    gameService.joinGame(p, Id(id))

    Redirect(routes.GameController.playGame(id))
  }

}
