package furhatos.app.furhatskill

/**
 *
 * Title: Player/p>
 *
 *
 * Description:
 *
 * Player Class for Tic Tac Toe game
 * Includes minimax algorithm for bot to use when it is bot's turn
 *
 *
 * @author Sam R. Thangiah
 *
 * @version 1.0
 */
class Player {
    //getters and setters
    private var isBot //checks to see if bot
            : Boolean
    var isTurn //checks if it is Player's turn
            = false
        private set
    var symbol //symbol of player for game X or O
            : String
        private set
    var opponent: String? = null
        private set
    private var moveCount: Int

    constructor() //constructor
    {
        isBot = false
        isTurn = false
        symbol = ""
        moveCount = 0
    }

    constructor(isBot: Boolean, sym: String) //overloaded constructor for player
    { //inputs if player is a bot, their symbol, and the turn
        this.isBot = isBot
        symbol = sym
        moveCount = 0
        if (symbol === "X") {
            isTurn = true
            opponent = "O"
        } else if (symbol === "O") {
            isTurn = false
            opponent = "X"
        } else {
            //X always goes first
            println("Symbol is invalid")
        }
    }

    fun setIsBot(bot: Boolean): Boolean {
        isBot = bot
        return true
    }

    fun setIsTurn(turn: Boolean): Boolean {
        isTurn = turn
        return true
    }

    fun setSymbol(sym: String): String //sets the symbols and sets the opponent based on the symbol
    {
        symbol = sym
        if (symbol === "X") //X always goes furst
        {
            isTurn = true
            opponent = "O"
        } else if (symbol === "O") {
            isTurn = false
            opponent = "X"
        }
        return symbol
    }

    private fun resetMoves(): Boolean {
        moveCount = 0
        return true
    }

    private fun incrementMoves(): Boolean {
        moveCount++
        return true
    }

    //decide for naming convention, gets best move
    fun decide(board: Board, depth: Int, player: Player): Int {
        return bestMove(board, depth, player)
    }

    //gets best move based on minimax scores returned
    private fun bestMove(board: Board, depth: Int, player: Player): Int {
        //minimax for the best solution
        var bestScore = -100 //best score for user
        var move = 0
        for (i in 0..8)  //checks for empty spaces (possible moves)
        {
            if (board.getBoardVal(i) === "-") {
                board.botMove(i + 1, player.symbol) //simulate move for bot
                val score = miniMax(board, 0, player, -1000, 1000, false)
                board.botMove(i + 1, "-") //undos move to check next
                if (score > bestScore) //if the score is better, replace and mark that move
                {
                    bestScore = score
                    move = i + 1 //funky indexing for easier user interaction
                }
            }
        }
        //board.botMove(move, player);
        println("The number of moves checked is $moveCount")
        resetMoves()
        return move
    }

    private fun checkMoves(board: Board): Boolean //checks if there are moves remaining
    {
        for (i in 0..8) {
            if (board.getBoardVal(i) === "-") {
                return true
            }
        }
        return false
    }

    /**
     *
     * @param board
     * @param depth
     * @param player
     * @param maximizing
     *
     * Minimax function is main purpose of demonstration
     * Essentially breaks down each move by running through each possibility in a tree format
     * Minimax goes turn by turn taking the min value then max value of children to make parent
     * Simulates a real game for each possible move remaining
     * Recursively calls itself to traverse down the tree to the lowest possible point, and end game outcome
     * Each end game outcome has its own score, allowing for traversal back up to get a best score
     * Best score is returned to allow for bestMove to choose the move based on returned score
     *
     * ALPHA AND BETA PRUNING
     * This v2 minimax includes alpha and beta pruning, essentially cutting off unnecessary outcomes that need checked by remembering what
     * the previous best move is for each player. If there is already a move that will be picked over a whole section of a tree,
     * that section of the tree can be removed to optimize the calculation time.
     * @return
     */
    private fun miniMax(board: Board, depth: Int, player: Player, alpha: Int, beta: Int, maximizing: Boolean): Int {
        var alpha = alpha
        var beta = beta
        val score = board.checkScore(player.symbol) //sets score of move
        if (score == 10) //returns a winning board
        {
            incrementMoves()
            return score
        }
        if (score == -10) //returns a losing board
        {
            incrementMoves()
            return score
        }
        if (!checkMoves(board)) {
            return 0
        }
        return if (maximizing) //if it is the bots turn in the simulation
        { //goal is to get best score
            var topScore = -100 //initial low score
            for (i in 0..8) {
                if (board.getBoardVal(i) === "-") //checks for empty spaces
                {
                    //recursive call for each open space, will place bot symbol and continue down tree
                    board.botMove(i + 1, player.symbol)
                    topScore = Math.max(topScore, miniMax(board, depth + 1, player, alpha, beta, !maximizing))
                    alpha = Math.max(
                        alpha,
                        topScore
                    ) //sets the alpha variable to the max of the current vs the found topScore
                    board.botMove(i + 1, "-") //unmakes move
                    if (beta <= alpha) {
                        break
                    }
                }
            }
            topScore - depth //returns best score for recursive score and best move
        } else  //if it is the user's turn in the simulation
        { //opponent wants you to have the worst score
            var lowScore = 100 //initial high score
            for (i in 0..8) {
                if (board.getBoardVal(i) === "-") //checks empty spaces
                {
                    //recursive call for each open space, will place player symbol and continue down tree
                    board.botMove(i + 1, player.opponent)
                    lowScore = Math.min(lowScore, miniMax(board, depth + 1, player, alpha, beta, !maximizing))
                    beta = Math.min(
                        beta,
                        lowScore
                    ) //sets the beta variable to the max of the current vs the found lowScore
                    board.botMove(i + 1, "-") //unmakes move
                    if (beta <= alpha) {
                        break
                    }
                }
            }
            return lowScore + depth //returns the lowest score for recursive call
        }
    }
}