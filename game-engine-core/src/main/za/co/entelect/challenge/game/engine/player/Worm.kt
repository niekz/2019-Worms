package za.co.entelect.challenge.game.engine.player

import za.co.entelect.challenge.game.engine.entities.WormsMap
import za.co.entelect.challenge.game.engine.map.MapCell
import za.co.entelect.challenge.game.engine.map.Point

abstract class Worm(var id: Int,
                    var health: Int,
                    var position: Point,
                    var weapon: Weapon,
                    val diggingRange: Int = 1,
                    val movementRange: Int = 1,
                    var player: WormsPlayer? = null) {

    var previousPosition = position
    var roundMoved = Int.MIN_VALUE
    var hitRound: Int = -1

    val dead: Boolean
        get() = health == 0

    fun moveTo(gameMap: WormsMap, target: Point) {
        gameMap[position].occupier = null

        previousPosition = position
        roundMoved = gameMap.currentRound
        position = target

        gameMap[position].occupier = this
    }


    fun takeDamage(damage: Int, round: Int) {
        health -= damage
        hitRound = round
    }

}
