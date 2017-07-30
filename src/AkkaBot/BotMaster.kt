package AkkaBot

import akka.actor.AbstractActor
import akka.actor.Props
import akka.actor.Terminated

class StartChildBot{}

class BotMaster: AbstractActor() {
    override fun preStart() {
        for( i in 1..10 ) {
            val child = getContext()
                .actorOf(Props.create(AkkaBot::class.java))
            getContext().watch(child)
        }
    }

    override fun createReceive(): Receive {
        return receiveBuilder()
            .match(StartChildBot::class.java) {
                val move = Direction.FORWARD
                for ( child in getContext().getChildren() ) {
                    println("Master started moving ${child}")
                    child.tell(Move(move), getSelf())
                }
            }
            .match(Terminated::class.java) {
                println("Child has stopped, starting a new one")
                val child = getContext()
                    .actorOf(Props.create(AkkaBot::class.java))
                getContext().watch(child)
            }
            .build()
    }
}