package furhatos.app.furhatskill.flow

import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.util.*
import furhatos.app.furhatskill.flow.*

val Idle: State = state {

    init {
        furhat.setVoice(Language.ENGLISH_US, Gender.MALE)
        if (users.count > 0) {
            furhat.attend(users.random)
        }
    }

    onEntry {
        furhat.ask("Hi there. Do you want to play a game?")
    }

    onResponse<Yes>{
        furhat.say("Awesome. We will be playing Tic Tac Toe.")
        goto(tictactoeGame)
        //furhat.run(furhatos.app.furhatskill.TicTacToe.kt)
    }

    onResponse<No>{
        //furhat.gesture(Gestures.Frown)
        furhat.say("That's sad, I was really hoping to play someone in Tic Tac Toe today.")
        terminate()
    }

}