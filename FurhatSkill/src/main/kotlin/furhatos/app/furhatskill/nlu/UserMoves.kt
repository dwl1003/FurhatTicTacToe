package furhatos.app.furhatskill.nlu

import furhatos.nlu.Intent
import furhatos.util.Language

//This intent returns the list of possible move answers, and an option to quit for the furhat to process

class UserMoves : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("top left", "top middle", "top right", "middle left", "middle middle",
                "middle right", "bottom left", "bottom middle", "bottom right", "quit", "metal metal",
                "metal left", "metal right", "top metal", "bottom metal")
    }
}