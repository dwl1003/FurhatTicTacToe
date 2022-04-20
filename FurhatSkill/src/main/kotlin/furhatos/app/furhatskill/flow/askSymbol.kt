package furhatos.app.furhatskill.flow

import furhatos.app.furhatskill.Board
import furhatos.app.furhatskill.Player
import furhatos.app.furhatskill.nlu.userSymbolO
import furhatos.app.furhatskill.nlu.userSymbolX
import furhatos.flow.kotlin.*
import furhatos.nlu.Intent
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.util.*

val askSymbol: State = state {
    onEntry {
        furhat.ask("Which symbol would you like to be, X or O?")
    }

    onResponse<userSymbolX>{
        furhat.say("Nice, you can go first")
        terminate("X")
    }

    onResponse<userSymbolO>{
        furhat.say("Alright cool, I'll go first then")
        terminate("O")
    }

}