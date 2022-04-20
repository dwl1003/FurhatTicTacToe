package furhatos.app.furhatskill.nlu

import furhatos.nlu.Intent
import furhatos.util.Language

class userMoves : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("topleft", "top left", "top-left", "topmiddle", "top middle", "top right", "middle left", "middle middle",
                "middle right", "bottom left", "bottom middle", "bottom right", "quit")
    }
}