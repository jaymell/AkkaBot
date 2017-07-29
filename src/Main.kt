import akka.actor.*
import AkkaBot.*

fun main(args: Array<String>) {
    val system: ActorSystem = ActorSystem.create()
    val akkaBot: ActorRef = system.actorOf(
        Props.create(AkkaBot::class.java),
       "akkaBot")

    akkaBot.tell(Move(Direction.FORWARD), ActorRef.noSender())
}


