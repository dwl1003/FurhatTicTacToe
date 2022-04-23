package furhatos.app.furhatskill.flow

import furhatos.app.furhatskill.Board
import furhatos.app.furhatskill.Player
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import java.util.*

val tictactoeGame: State = state() {
    onEntry {
                furhat.say("Lets begin. Set up the board.")
                call(askRules)
                //Initialize the board, players, winner string, user choice, and loop booleans
                val board = Board()
                val player = Player(false, "X")
                val bot = Player(true, "O")
                var hasWinner = ""
                var chooseSymbol: String
                var userChoice: Int
                var playerMove = false
                var symbolLoop = true

                //create a scanner for user input
                val choice = Scanner(System.`in`)
                while (symbolLoop) {
                    //println("Choose a symbol (1 for X, 2 for O)")
                    chooseSymbol = call(askSymbol) as String
                    if (chooseSymbol == "X") {
                        player.setSymbol("X")
                        bot.setSymbol("O")
                        symbolLoop = false
                    } else if (chooseSymbol == "O") {
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
                            userChoice = call(userMove) as Int //gets user input
                            if (userChoice == 99) //quits if user enter exit input
                            {
                                furhat.say("Wow, you must've been really scared to face a genius robot")
                                terminate()
                            }
                            when (userChoice) {
                                in 1..9 //checks to make sure choice is in bounds
                                -> {
                                    playerMove = board.makeMove(userChoice, player.symbol) == true
                                }
                                else  //loops if input it out of bounds
                                -> {
                                    playerMove = false
                                    furhat.say("You have to choose a move inside one through nine")
                                }
                            }
                        } else  //bot's move
                        {
                            furhat.say("Thinking about my move...")
                            val move = bot.decide(board, 0, bot)
                            board.makeMove(move, bot.symbol)
                            when(move) {
                                1 -> furhat.say("I'll place my " + bot.symbol + " at the top left spot")
                                2 -> furhat.say("I'll place my " + bot.symbol + " at the top middle spot")
                                3 -> furhat.say("I'll place my " + bot.symbol + " at the top right spot")
                                4 -> furhat.say("I'll place my " + bot.symbol + " at the middle left spot")
                                5 -> furhat.say("I'll place my " + bot.symbol + " at the middle middle spot")
                                6 -> furhat.say("I'll place my " + bot.symbol + " at the middle right spot")
                                7 -> furhat.say("I'll place my " + bot.symbol + " at the bottom left spot")
                                8 -> furhat.say("I'll place my " + bot.symbol + " at the bottom middle spot")
                                9 -> furhat.say("I'll place my " + bot.symbol + " at the bottom right spot")
                                else -> furhat.say("Something went wrong, I messed up")
                            }
                            playerMove = true
                        }
                    }
                    hasWinner = board.checkWinner() //checks for winner

                    //end of game cases, or swaps turn
                    if (hasWinner === "X") {
                        if(bot.symbol == "X")
                        {
                            furhat.say("Wow, looks like I won this one. What are the odds")
                        }
                        else
                        {
                            furhat.say("Unlucky, I really shouldn't have lost. Blame my programmers")
                        }
                        break
                    } else if (hasWinner === "O") {
                        if(bot.symbol == "O")
                        {
                            furhat.gesture(Gestures.BigSmile)
                            furhat.say("Wow, looks like I won this one. What are the odds")
                        }
                        else
                        {
                            furhat.gesture(Gestures.ExpressSad)
                            furhat.say("Unlucky, I really shouldn't have lost. Blame my programmers")
                        }
                        break
                    } else if (hasWinner === "tie") {
                        furhat.say("Seems we were evenly matched, this game was a tie")
                        furhat.gesture(Gestures.ExpressAnger)
                        furhat.say("I blame my creators for me not winning")
                        println("The game ends in a tie")
                        break
                    } else if (player.isTurn && hasWinner === "turn") {
                        player.setIsTurn(false)
                        bot.setIsTurn(true)
                    } else if (bot.isTurn && hasWinner === "turn") {
                        player.setIsTurn(true)
                        bot.setIsTurn(false)
                    }
                    playerMove = false
                }
        goto(Idle)
    }
}
