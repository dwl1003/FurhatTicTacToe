package furhatos.app.furhatskill.flow

import furhatos.app.furhatskill.Board
import furhatos.app.furhatskill.Player
import furhatos.app.furhatskill.nlu.userMoves
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.util.*

val userMove: State = state {
    onEntry {
        furhat.ask("Where would you like to place your piece?")
    }

    onResponse<userMoves>{
        furhat.say("Alright, I'll make my move now")

        if(it.text == "top left")
            terminate(1)
        else if(it.text == "top middle")
            terminate(2)
        else if(it.text == "top right")
            terminate(3)
        else if(it.text == "middle left")
            terminate(4)
        else if(it.text == "middle middle")
            terminate(5)
        else if(it.text == "middle right")
            terminate(6)
        else if(it.text == "bottom left")
            terminate(7)
        else if(it.text == "bottom middle")
            terminate(8)
        else if(it.text == "bottom right")
            terminate(9)
    }

}