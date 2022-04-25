package furhatos.app.furhatskill.flow

import furhatos.app.furhatskill.nlu.UserMoves
import furhatos.flow.kotlin.*

//This state returns the player's move as an integer to use in the game positioning

val userMove: State = state {
    onEntry {
        furhat.ask({
                random {
                    +"Where would you like to place your piece?"
                    +"Take your time to decide, then tell me where your piece will go."
                    +"Which spot will you put your piece?"
                    }
                }) //prompt user for move
        }

    onResponse<UserMoves>{
        furhat.say({
            random {
                +"I'll decide where to play now."
                block{
                    +"Give me a second to think."
                    +delay(3000)
                }
                +"Hmm, what should I do?"
            }
        })

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