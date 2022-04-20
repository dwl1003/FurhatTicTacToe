package furhatos.app.furhatskill

import furhatos.app.furhatskill.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*
import furhatos.app.furhatskill.Board
import furhatos.app.furhatskill.Player
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.util.*

class FurhatskillSkill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
