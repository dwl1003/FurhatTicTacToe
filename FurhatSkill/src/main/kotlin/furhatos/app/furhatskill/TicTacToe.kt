package furhatos.app.furhatskill

import kotlin.jvm.JvmStatic
import furhatos.app.furhatskill.Board
import furhatos.app.furhatskill.Player
import java.util.*

/**
 *
 * Title: TicTacToe
 *
 *
 * Description:
 * Tic Tac Toe game using the minimax algorithm to generate a list of moves for the bot to use
 * Bot will choose optimal move to play against the player
 *
 *
 * @author Sam R. Thangiah
 *
 * @version 1.0
 */
object TicTacToe {
    @JvmStatic
    fun main(args: Array<String>) {
        //Initialize the board, players, winner string, userchoice, and loop booleans
        val board = Board()
        val player = Player(false, "X")
        val bot = Player(true, "O")
        var hasWinner = ""
        var chooseSymbol: Int
        var userChoice: Int
        var playerMove = false
        var symbolLoop = true


        //create a scanner for user input
        val choice = Scanner(System.`in`)
        while (symbolLoop == true) {
            println("Choose a symbol (1 for X, 2 for O)")
            chooseSymbol = choice.nextInt()
            if (chooseSymbol == 1) {
                player.setSymbol("X")
                bot.setSymbol("O")
                symbolLoop = false
            } else if (chooseSymbol == 2) {
                player.setSymbol("O")
                bot.setSymbol("X")
                symbolLoop = false
            } else {
                symbolLoop = true
            }
            println(chooseSymbol)
        }
        board.printIndexBoard()
        //begin turn loop
        while (hasWinner !== "X" || hasWinner !== "O" || hasWinner !== "tie") //each turn check winner
        {
            while (!playerMove) //checks to make sure the player move is still false
            {
                if (player.isTurn) {
                    println("Choose a position (1-9), auto move with 777, exit by entering 99")
                    userChoice = choice.nextInt() //gets user input
                    if (userChoice == 99) //quits if user enter exit input
                    {
                        return
                    }
                    when (userChoice) {
                        in 1..9 //checks to make sure choice is in bounds
                        -> {
                            playerMove = board.makeMove(userChoice, player.symbol) == true
                        }
                        777 //auto move for the player, if they choose
                        -> {
                            println("Auto making move...")
                            board.makeMove(player.decide(board, 0, player), player.symbol)
                            playerMove = true
                        }
                        else  //loops if input it out of bounds
                        -> {
                            playerMove = false
                            println("Move is out of index, choose 1-9")
                        }
                    }
                } else  //bot's move
                {
                    println("Bot is making its move")
                    board.makeMove(bot.decide(board, 0, bot), bot.symbol)
                    playerMove = true
                }
            }
            hasWinner = board.checkWinner() //checks for winner

            //end of game cases, or swaps turn
            if (hasWinner === "X") {
                board.printBoard()
                println("X is the winner")
                player.setIsWinner(true)
                break
            } else if (hasWinner === "O") {
                board.printBoard()
                println("O is the winner")
                bot.setIsWinner(true)
                break
            } else if (hasWinner === "tie") {
                board.printBoard()
                println("The game ends in a tie")
                break
            } else if (player.isTurn == true && hasWinner === "turn") {
                player.setIsTurn(false)
                bot.setIsTurn(true)
            } else if (bot.isTurn == true && hasWinner === "turn") {
                player.setIsTurn(true)
                bot.setIsTurn(false)
            }
            board.printBoard()
            playerMove = false
        }
        //closes scanner
        choice.close()
    }
}