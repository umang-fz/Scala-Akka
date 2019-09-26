import akka.actor.{Actor, ActorRef, Props}
import scala.io.Source.fromFile

class WordCounterActor(fileName: String) extends Actor {
  private var running = false
  private var totalLine = 0
  private var linesProcessed = 0
  private var result = 0
  private var fileSender: Option[ActorRef] = None
  override def receive: Receive = {
    case StartProcessFileMessage() =>
      if(running) println("Warning: Duplicate start message received")
      else  {
        running = true
        fileSender = Some(sender)
        fromFile(fileName).getLines.foreach{
          line =>
            context.actorOf(Props[StringProcessingActor]) ! ProcessStrMessage(line)
            totalLine+=1
        }
      }
    case StringProcessedMessage(noOfWords) =>
      result+=noOfWords
      linesProcessed+=1
      if(linesProcessed == totalLine) {
        fileSender.get ! result
      }
    case _ => println("Message not recieved")
  }
}
