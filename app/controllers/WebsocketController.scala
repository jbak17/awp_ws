package controllers

import play.api.mvc._
import play.api.libs.streams.ActorFlow
import javax.inject.{Inject, Singleton}

import akka.actor._
import akka.stream._
import play.api.libs.json.JsValue


object WebSocketActor {
  def props(out: ActorRef) = Props(new WebSocketActor(out))
}

class WebSocketActor(out: ActorRef) extends Actor {

  var counter = 0;

  out ! "Hello from the server"

  override def receive: Receive = {
    case msg: String =>
      counter += 1
      println(counter +": " + msg)
      out ! (counter + ": server")
//    case msg: JsValue =>
//      println("json received")
//      out ! msg
  }

  override def postStop() {
    println("Disconnected.")
  }

}


@Singleton
class WebsocketController @Inject()(cc:ControllerComponents) (implicit system: ActorSystem, mat: Materializer) extends AbstractController(cc) {

  def socket = WebSocket.accept[String, String] { request =>
    ActorFlow.actorRef(out => WebSocketActor.props(out))
  }

}
