import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import akka.pattern.ask

import scala.concurrent.Await
import scala.concurrent.duration._

object RunExample extends App {
  val system = ActorSystem("StringProcessing")
  val actor = system.actorOf(Props(new WordCounterActor(args(0))))
  implicit val timeout = Timeout(25 seconds)
  val future = actor ? StartProcessFileMessage()
  val resultFromFuture = Await.result(future, timeout.duration).asInstanceOf[Int]
  println(resultFromFuture)
  system terminate
}
