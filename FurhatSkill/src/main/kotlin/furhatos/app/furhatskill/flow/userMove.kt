package furhatos.app.furhatskill.flow

import furhatos.app.furhatskill.nlu.UserMoves
import furhatos.flow.kotlin.*

//This state returns the player's move as an integer to use in the game positioning

val userMove: State = state {
    onEntry {
        if (users.count > 0) {
            furhat.attend(users.random)
        }
        furhat.ask({
                random {
                    +"Where would you like to place your piece?"
                    +"Take your time to decide, then tell me where your piece will go."
                    +"Which spot will you put your piece?"
                    }
                }, 5000) //prompt user for move
        }

    onResponse<UserMoves>{

        when (it.text) {
            "top left" -> terminate(1)
            "top middle" -> terminate(2)
            "top metal" -> terminate(2)
            "top right" -> terminate(3)
            "middle left" -> terminate(4)
            "metal left" -> terminate(4)
            "middle middle" -> terminate(5)
            "metal metal" -> terminate(5)
            "middle right" -> terminate(6)
            "metal right" -> terminate (6)
            "bottom left" -> terminate(7)
            "bottom middle" -> terminate(8)
            "bottom metal" -> terminate(8)
            "bottom right" -> terminate(9)
            "quit" -> terminate(99)
            else -> {
                furhat.say("Sorry, my microphone did not pick that up well. Try again.")
                reentry()
            }
        }
    }

}