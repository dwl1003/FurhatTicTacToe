package furhatos.app.furhatskill

/**
 *
 * Title: Board/p>
 *
 *
 * Description:
 *
 * Board class for the tic-tac-toe game
 * Handles scoring for minimax based on the board
 *
 *
 * @author Sam R. Thangiah
 *
 * @version 1.0
 */
class Board {
    //board class
    private final val boardSize = 9
    private val board = arrayOfNulls<String>(boardSize) //Board is a standard array of Strings

    init  //constructor
    {
        for (i in 0..8) {
            board[i] = "-"
        }
    }

    fun getBoardVal(i: Int): String? {
        return board[i]
    }

    fun printBoard() //ASCII Tic Tac Toe Board
    {
        println("|---|---|---|")
        println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |")
        println("|-----------|")
        println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |")
        println("|-----------|")
        println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |")
        println("|---|---|---|")
        println("===============")
    }

    fun printIndexBoard() //ASCII Prints the indexes for input
    {
        println("|---|---|---|")
        println("| 1 | 2 | 3 |")
        println("|-----------|")
        println("| 4 | 5 | 6 |")
        println("|-----------|")
        println("| 7 | 8 | 9 |")
        println("|---|---|---|")
        println("===============")
    }

    fun makeMove(location: Int, symbol: String?): Boolean //Makes a move based on the player symbol
    {
        var location = location
        location -= 1
        return if (board[location] === "-") //Checks to see if spot is open
        {
            board[location] = symbol
            true
        } else {
            println("Spot is already taken")
            false
        }
    }

    fun botMove(location: Int, symbol: String?): Boolean //Makes a move anywhere despite if spot is take or not
    { //Mostly used for the minimax moves in order to replace symbols with a blank symbol
        var location = location
        location -= 1
        board[location] = symbol
        return true
    }

    fun checkWinner(): String //Checks to see if there is a winner
    {
        //check winner
        for (a in 0..7) { //For loop for each case
            var line: String? = null
            when (a) {
                0 -> line = board[0] + board[1] + board[2]
                1 -> line = board[3] + board[4] + board[5]
                2 -> line = board[6] + board[7] + board[8]
                3 -> line = board[0] + board[3] + board[6]
                4 -> line = board[1] + board[4] + board[7]
                5 -> line = board[2] + board[5] + board[8]
                6 -> line = board[0] + board[4] + board[8]
                7 -> line = board[2] + board[4] + board[6]
            }
            //For X winner
            if (line == "XXX") {
                return "X"
            } else if (line == "OOO") {
                return "O"
            }
        }
        var emptyCount = 0 //checks for empty spaces
        for (i in 0..8) {
            if (board[i] === "-") {
                emptyCount++
            }
        }
        return if (emptyCount == 0) {
            "tie"
        } else "turn"
        //just returns to the next turn of game
    }

    fun checkScore(symbol: String): Int //score checker for minimax, same as the checkWinner but for minimax leaves
    {
        //check winner
        for (a in 0..7) { //loop through all cases
            var line: String? = null
            when (a) {
                0 -> line = board[0] + board[1] + board[2]
                1 -> line = board[3] + board[4] + board[5]
                2 -> line = board[6] + board[7] + board[8]
                3 -> line = board[0] + board[3] + board[6]
                4 -> line = board[1] + board[4] + board[7]
                5 -> line = board[2] + board[5] + board[8]
                6 -> line = board[0] + board[4] + board[8]
                7 -> line = board[2] + board[4] + board[6]
            }
            //if X is the winner
            if (line == "XXX") {
                if (symbol === "X") {
                    return 10 //return a good score if you are X, and you win
                } else if (symbol === "O") {
                    return -10 //return a bad score if you are O, and X won
                }
            } else if (line == "OOO") //if O is the winner
            {
                if (symbol === "X") {
                    return -10 //return a bad score if you are X, and O won
                } else if (symbol === "O") {
                    return 10 //return a good score if you are O, and you win
                }
            }
        }
        return 0
    }
}