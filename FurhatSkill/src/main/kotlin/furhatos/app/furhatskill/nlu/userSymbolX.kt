package furhatos.app.furhatskill.nlu

import furhatos.nlu.Intent
import furhatos.util.Language

class userSymbolX : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("X", "x", "ex", "ax", "ox")
    }
}