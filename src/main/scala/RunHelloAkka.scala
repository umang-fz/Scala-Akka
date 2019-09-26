import akka.actor.ActorSystem
import akka.actor.Props
object RunHelloAkka extends App {
  var actorSystem = ActorSystem("ActorSystem");                       // Creating ActorSystem
  var actor = actorSystem.actorOf(Props[HelloAkka],"HelloAkka")        //Creating actor
  actor ! "Hello Akka"                                                // Sending messages by using !
  actor ! 100.52
}
