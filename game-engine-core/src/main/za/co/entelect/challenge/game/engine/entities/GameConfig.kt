package za.co.entelect.challenge.game.engine.entities

import za.co.entelect.challenge.game.engine.player.Weapon
import kotlin.jvm.Transient
import kotlin.random.Random


//TODO: Read from file for JVM module.
//Plus inject it everywhere as far as possible. We don't necessarily need a DI framework, but do something like it for testability.
class GameConfig {

    val maxRounds = 0
    val maxDoNothings = 10
    val commandoWorms = PlayerWormDefinition(2, 100, Weapon(1, 3))
    val pushbackDamage = 5
    var seed: Long = 0
        set(value) {
            field = value
            random = Random(seed)
        }

    val mapColumns = 32
    val mapRows = 32

    @Transient
    var random: Random = Random

    class PlayerWormDefinition(val count: Int,
                               val initialHp: Int,
                               val weapon: Weapon)
}
