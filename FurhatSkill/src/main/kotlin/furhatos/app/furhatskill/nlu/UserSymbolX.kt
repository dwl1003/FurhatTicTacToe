package furhatos.app.furhatskill.nlu

import furhatos.nlu.Intent
import furhatos.util.Language

//This intent returns a list of possible pronunciations of the letter X for choosing a symbol

class UserSymbolX : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("X", "x", "ex", "ax", "ox", "eggs", "ecks")
    }
}