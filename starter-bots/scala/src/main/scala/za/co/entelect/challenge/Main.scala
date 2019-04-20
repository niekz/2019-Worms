package za.co.entelect.challenge

import java.nio.file.{Files, Paths}

import org.json4s.DefaultFormats

import scala.io.StdIn.readInt
import za.co.entelect.challenge.commands.Command
import za.co.entelect.challenge.entities.GameState

import scala.util.Random
import org.json4s._
import org.json4s.native.JsonMethods._

object Main {
  private final val ROUNDS_DIRECTORY = "rounds"
  private final val STATE_FILE_NAME  = "state.json"

  implicit val formats = DefaultFormats

  /**
    * Read the current state, feed it to the bot, get the output and print it to stdout
    *
    * @param args the args
    */
  def main(args: Array[String]): Unit = {
    while (true) {
      try {
        val roundNumber: Int = readInt()
        val random = new Random(System.nanoTime())

        val statePath = s"./$ROUNDS_DIRECTORY/$roundNumber/$STATE_FILE_NAME"
        val state     = Files.readAllBytes(Paths.get(statePath))

        val gameState: GameState = parse(state.toString).extract[GameState]
        val command:   Command   = new Bot(random = random, gameState = gameState).run()

        println(s"C;$roundNumber;${command.render()}")
      } catch {
        case e: Exception => e.printStackTrace()
      }
    }
  }
}