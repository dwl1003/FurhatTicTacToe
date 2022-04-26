package furhatos.app.furhatskill.flow

import furhatos.app.furhatskill.Board
import furhatos.app.furhatskill.Player
import furhatos.flow.kotlin.*
import furhatos.nlu.Intent
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.util.*

val askPlayAgain: State = state{
    onEntry {
        if (users.count > 0) {
            furhat.attend(users.random)
        }
        furhat.ask("Would you like to play again?",5000)
    }

    onResponse<Yes>{
        terminate(true)
    }

    onResponse<No>{
        terminate(false)
    }
}