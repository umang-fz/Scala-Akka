import akka.actor.Actor
class HelloAkka extends Actor{

  override def receive: Receive = {
    case msg:String => println(msg)
    case _ =>println("Unknown message")
  }

}