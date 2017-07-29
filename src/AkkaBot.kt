package AkkaBot

import akka.actor.AbstractActor

enum class Direction {
    FORWARD,
    BACKWARDS,
    RIGHT,
    LEFT
}

data class Move(val direction: Direction?)
class Stop {}
class GetRobotState {}
data class RobotState(val direction: Direction,
                      val moving: Boolean)

class AkkaBot : AbstractActor() {
    private var moving = false
    private var direction: Direction? = null

    override fun createReceive(): Receive {
        return receiveBuilder()
                .match(Move::class.java, this::onMove)
                .match(Stop::class.java, this::onStop)
                .build()
    }

    fun onMove(move: Move) {
        moving = true
        direction = move.direction
        println("I am now moving")
    }

    fun onStop(stop: Stop) {
        moving = false;
        println("I stopped moving")
    }
}
