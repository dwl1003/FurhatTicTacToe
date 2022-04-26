package furhatos.app.furhatskill.flow

import furhatos.app.furhatskill.Board
import furhatos.app.furhatskill.Player
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import java.util.*

val tictactoeGame: State = state() {
    onEntry {
                furhat.say("Let's begin. Set up the tic tac toe board.")
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
                var playAgain = false

                //create a scanner for user input
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
                                    furhat.say("You have to choose a spot that isn't already taken.")
                                }
                            }
                            if(!playerMove)
                            {
                                furhat.say("You have to choose a spot that isn't already taken.")
                            }
                        } else  //bot's move
                        {
                            furhat.say({
                                random {
                                    +"I'll decide where to play now."
                                    block{
                                        +"Give me a second to think."
                                        +delay(1750)
                                        +"Alright. I'm ready."
                                    }
                                    +"Hoowee, what should I do?"
                                }
                            })
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
                        delay(500)
                        if(bot.symbol == "X")
                        {
                            furhat.ledStrip.solid(java.awt.Color(127,0,0))
                            furhat.ledStrip.solid(java.awt.Color(0,127,0))
                            furhat.ledStrip.solid(java.awt.Color(0,0,127))
                            furhat.gesture(Gestures.BigSmile)
                            furhat.say({
                                random {
                                        +"Wow, looks like I won this one. What are the odds"
                                        +"I must have gotten lucky, I usually don't do this well"
                                        +"I guess I win, better luck next time."
                                }
                            })
                        }
                        else
                        {
                            furhat.say("Unlucky, I really shouldn't have lost. Blame my programmers")
                        }
                        break
                    } else if (hasWinner === "O") {
                        if(bot.symbol == "O")
                        {
                            furhat.ledStrip.solid(java.awt.Color(127,0,0))
                            furhat.ledStrip.solid(java.awt.Color(0,127,0))
                            furhat.ledStrip.solid(java.awt.Color(0,0,127))
                            furhat.gesture(Gestures.BigSmile)
                            furhat.say({
                                random {
                                    +"Wow, looks like I won this one. What are the odds"
                                    +"I must have gotten lucky, I usually don't do this well"
                                    +"I guess I win, better luck next time."
                                }
                            })
                        }
                        else
                        {
                            furhat.gesture(Gestures.ExpressSad)
                            furhat.say("Unlucky, I really shouldn't have lost. Blame my programmers")
                        }
                        break
                    } else if (hasWinner === "tie") {
                        furhat.say({
                            random {
                                +"Seems like we were evenly matched, good game."
                                +"We tied, but I won't let that happen again."
                                +"We may have tied, but that's a loss for me."
                            }
                        })
                        furhat.gesture(Gestures.ExpressAnger)
                        furhat.say("I blame my programmers for me not winning")
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
        playAgain = call(askPlayAgain) as Boolean
        if(playAgain)
        {
            furhat.gesture(Gestures.BigSmile)
            furhat.say("Sweet, glad you enjoyed playing with me.")
            delay(1000)
            reentry()
        }
        else{
            furhat.say("No worries, I am pretty hard to beat with all the practice I get.")
            delay(5000)
            goto(Idle)
        }
    }
}
