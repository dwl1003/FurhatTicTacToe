package furhatos.app.furhatskill.nlu

import furhatos.nlu.Intent
import furhatos.util.Language

class userSymbolO : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("O", "o", "oh", "ow", "OH")
    }
}