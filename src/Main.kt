import akka.actor.ActorSystem
import akka.actor.ActorRef
import akka.actor.Props
import AkkaBot.*

fun main(args: Array<String>) {
    val system: ActorSystem = ActorSystem.create()
    val masterBot: ActorRef = system.actorOf(
        Props.create(BotMaster::class.java),
        "botMaster")

    masterBot.tell(StartChildBot(), ActorRef.noSender())

}

