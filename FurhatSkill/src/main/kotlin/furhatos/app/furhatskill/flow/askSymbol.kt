package furhatos.app.furhatskill.flow

import furhatos.app.furhatskill.Board
import furhatos.app.furhatskill.Player
import furhatos.app.furhatskill.nlu.UserSymbolO
import furhatos.app.furhatskill.nlu.UserSymbolX
import furhatos.flow.kotlin.*
import furhatos.nlu.Intent
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.util.*

val askSymbol: State = state {
    onEntry {
        if (users.count > 0) {
            furhat.attend(users.random)
        }
        furhat.ask("Which symbol would you like to be, X or O?")
    }

    onResponse<UserSymbolX>{
        furhat.say {
            random {
                +"Alright, you can go first"
                +"Okay, X always goes first, so you'll start us off"
                +"Cool, you go first."
            }
        }
        terminate("X")
    }

    onResponse<UserSymbolO>{
        furhat.say {
            random {
                +"Alright, I'll go first then."
                +"Okay, X always goes first, so I'll start us off"
                +"Cool, I'll make the first move then."
            }
        }
        terminate("O")
    }

}