package controllers

import play.api.mvc._
import play.api.libs.streams.ActorFlow
import javax.inject.Inject

import akka.actor._
import akka.stream._
import play.api.libs.json.JsValue

/*
object Actors{
  var actorsList = scala.collection.mutable.ArrayBuffer.empty[ActorRef]
}

object WebSocketActor {
  def props(ref: ActorRef) = Props(new WebSocketActor(ref))
}

class WebSocketActor(ref: ActorRef) extends Actor {
  var actors = Actors

  def receive = {
    case msg: JsValue =>
      actors.actorsList.map( actor =>
        actor ! msg)
  }

  override def preStart(): Unit = {
    actors.actorsList += ref
    println("Actor connected")
  }

  override def postStop(): Unit = {
    actors.actorsList -= ref
    println("Actor disconnected")
  }
}
def socket = WebSocket.accept[JsValue, JsValue] { request =>
ActorFlow.actorRef { ref =>
WebSocketActor.props(ref)
}
}
*/

class WebsocketController @Inject()(cc:ControllerComponents) (implicit system: ActorSystem, mat: Materializer) extends AbstractController(cc) {

  object WebSocketActor {
    def props(out: ActorRef) = Props(new WebSocketActor(out))
  }

  class WebSocketActor(out: ActorRef) extends Actor {

    out ! "Hello from the WebSocketActor"

    override def receive: Receive = {
      case msg: String =>
        println("From server:")
        println(msg)
        out ! msg
    }

    override def postStop() {
      println("Disconnected.")
    }

  }


  def socket = WebSocket.accept[String, String] { request =>
    ActorFlow.actorRef(out => WebSocketActor.props(out))
  }

}
