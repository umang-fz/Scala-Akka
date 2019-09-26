import akka.actor.Actor

class StringProcessingActor extends Actor {
  override def receive: Receive = {
    case ProcessStrMessage(str) => {
      val wordsInStr = str.split(" ").length
      sender ! StringProcessedMessage(wordsInStr)
    }
    case _ => println("Error: message not recognized")
  }
}