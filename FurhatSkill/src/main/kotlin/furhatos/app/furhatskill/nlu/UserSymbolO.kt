package furhatos.app.furhatskill.nlu

import furhatos.nlu.Intent
import furhatos.util.Language

//This intent returns a list of possible pronunciations of the letter O for choosing a symbol

class UserSymbolO : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("O", "o", "oh", "ow", "OH")
    }
}