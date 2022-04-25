package furhatos.app.furhatskill.flow

import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.util.*
import furhatos.app.furhatskill.flow.*
import furhatos.gestures.Gestures

val Idle: State = state {

    init {
        furhat.setVoice(Language.ENGLISH_US, Gender.MALE)
        if (users.count > 0) {
            furhat.attend(users.random)
        }
    }

    onEntry {
        furhat.ask( {
            random {
                +"Hey, wanna play a game with me?"
                +"Hey there, interested in playing a game?"
                +"Is it game time?"
            }
        })
    }

    onResponse<Yes>{
        furhat.gesture(Gestures.BigSmile)
        furhat.say({
             random{
                        +"Awesome. We will be playing Tic Tac Toe."
                        block {
                            +Gestures.Wink
                            +"Cool, good luck playing tic tac toe against me."}
                        block {
                            +Gestures.CloseEyes
                            +"I hope my programmers did this right. We will be playing Tic Tac Toe."}
                }})
        goto(tictactoeGame)
    }


    onResponse<No>{
        furhat.say( {
            random {
                block {+Gestures.ExpressSad
                    +"That's sad, I was really hoping to play someone in Tic Tac Toe today." }
                block {+Gestures.ExpressDisgust
                    +"I'm not upset, I'm just disappointed. I'll find somebody else then" }
                block {+Gestures.ExpressAnger
                    +"That's not cool man, it's probably my birthday and I wanted to play today" }
            }
        })
        delay(3000)
        reentry()
    }

}