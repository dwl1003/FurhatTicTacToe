package furhatos.app.furhatskill.flow

import furhatos.app.furhatskill.Board
import furhatos.app.furhatskill.Player
import furhatos.flow.kotlin.*
import furhatos.nlu.Intent
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.util.*

val askRules: State = state{
    onEntry {
        furhat.ask("Would you like to learn the rules, and directions to play with me?")
    }

    onResponse<Yes>{
        furhat.say("To play, the rows are the top, middle, and bottom." +
                "The columns are the left, middle, and right" +
                "When you tell me your move, tell me a combination of row and column" +
                "For example, top left, middle middle, or bottom right" +
                "Your goal is to get 3 of your symbol in a row, either in a column, in a row," +
                "or diagonally. Let's start the game!")
        terminate()
    }

    onResponse<No>{
        furhat.say("Alright cool, lets begin!")
        terminate()
    }
}