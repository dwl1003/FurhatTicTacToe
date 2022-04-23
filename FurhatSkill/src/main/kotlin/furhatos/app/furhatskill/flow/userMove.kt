package furhatos.app.furhatskill.flow

import furhatos.app.furhatskill.nlu.UserMoves
import furhatos.flow.kotlin.*

//This state returns the player's move as an integer to use in the game positioning

val userMove: State = state {
    onEntry {
        furhat.ask("Where would you like to place your piece?") //prompt user for move
    }

    onResponse<UserMoves>{
        furhat.say("Alright, I'll make my move now")

        when (it.text) {
            "top left" -> terminate(1)
            "top middle" -> terminate(2)
            "top right" -> terminate(3)
            "middle left" -> terminate(4)
            "middle middle" -> terminate(5)
            "middle right" -> terminate(6)
            "bottom left" -> terminate(7)
            "bottom middle" -> terminate(8)
            "bottom right" -> terminate(9)
        }
    }

}