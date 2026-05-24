package com.example.data

data class WordDefinition(
    val english: String,
    val translation: String,
    val ipa: String, // Phonetic spelling
    val partOfSpeech: String, // e.g. "verb", "noun", "adjective"
    val definitionEs: String, // Definition in Spanish
    val definitionEn: String, // Definition in English
    val examples: List<ExampleSentence>,
    val synonyms: List<String> = emptyList()
)

data class ExampleSentence(
    val english: String,
    val spanish: String
)

object DictionaryData {
    val localWords = listOf(
        WordDefinition(
            english = "Achieve",
            translation = "Lograr / Conseguir",
            ipa = "/əˈtʃiːv/",
            partOfSpeech = "Verbo",
            definitionEs = "Alcanzar un objetivo, meta o éxito mediante esfuerzo, habilidad o perseverancia.",
            definitionEn = "Successfully reach or attain a goal, objective, or success by effort, skill, or courage.",
            examples = listOf(
                ExampleSentence("She worked hard to achieve her dreams.", "Ella trabajó duro para lograr sus sueños."),
                ExampleSentence("We can achieve anything if we work together.", "Podemos lograr lo que sea si trabajamos juntos.")
            ),
            synonyms = listOf("Attain", "Accomplish", "Reach", "Fulfill")
        ),
        WordDefinition(
            english = "Aesthetic",
            translation = "Estético / Estética",
            ipa = "/iːsˈθet.ɪk/",
            partOfSpeech = "Adjetivo / Sustantivo",
            definitionEs = "Relativo a la apreciación de la belleza o el arte; que tiene un aspecto visual agradable.",
            definitionEn = "Concerned with beauty or the appreciation of beauty; giving or designed to give pleasure through beauty.",
            examples = listOf(
                ExampleSentence("The building's design has a modern aesthetic.", "El diseño del edificio tiene una estética moderna."),
                ExampleSentence("The picture is very aesthetically pleasing.", "La imagen es muy agradable estéticamente.")
            ),
            synonyms = listOf("Artistic", "Beautiful", "Tasteful", "Visual")
        ),
        WordDefinition(
            english = "Ambiguous",
            translation = "Ambiguo / Poco claro",
            ipa = "/æmˈbɪɡ.ju.əs/",
            partOfSpeech = "Adjetivo",
            definitionEs = "Que puede entenderse de varios modos o admitir distintas interpretaciones.",
            definitionEn = "Open to more than one interpretation; having a double meaning or unclear.",
            examples = listOf(
                ExampleSentence("His message was ambiguous and left us confused.", "Su mensaje fue ambiguo y nos dejó confundidos."),
                ExampleSentence("The ending of the movie was intentionally ambiguous.", "El final de la película fue intencionalmente ambiguo.")
            ),
            synonyms = listOf("Vague", "Equivocal", "Unclear", "Obscure")
        ),
        WordDefinition(
            english = "Breakthrough",
            translation = "Avance / Descubrimiento",
            ipa = "/ˈbreɪk.θruː/",
            partOfSpeech = "Sustantivo",
            definitionEs = "Un paso adelante o descubrimiento importante, especialmente en la ciencia o medicina.",
            definitionEn = "A sudden, dramatic, and important discovery or development, especially in science.",
            examples = listOf(
                ExampleSentence("Scientists made a major breakthrough in cancer research.", "Los científicos lograron un gran avance en la investigación del cáncer."),
                ExampleSentence("This new technology represents a huge breakthrough.", "Esta nueva tecnología representa un gran descubrimiento.")
            ),
            synonyms = listOf("Discovery", "Advance", "Innovation", "Development")
        ),
        WordDefinition(
            english = "Challenge",
            translation = "Desafío / Reto",
            ipa = "/ˈtʃæl.ɪndʒ/",
            partOfSpeech = "Sustantivo / Verbo",
            definitionEs = "Una tarea o situación difícil que pone a prueba la habilidad o fuerza de alguien.",
            definitionEn = "A call to take part in a contest or competition; a demanding task that tests someone's ability.",
            examples = listOf(
                ExampleSentence("Learning English is a rewarding challenge.", "Aprender inglés es un desafío gratificante."),
                ExampleSentence("He challenged me to a game of chess.", "Él me desafió a una partida de ajedrez.")
            ),
            synonyms = listOf("Obstacle", "Difficulty", "Dare", "Test")
        ),
        WordDefinition(
            english = "Develop",
            translation = "Desarrollar / Crecer",
            ipa = "/dɪˈvel.əp/",
            partOfSpeech = "Verbo",
            definitionEs = "Hacer progresar, crecer o perfeccionar una idea, software, producto o habilidad con el tiempo.",
            definitionEn = "Grow or cause to grow and become more mature, advanced, or elaborate.",
            examples = listOf(
                ExampleSentence("Children develop language skills incredibly fast.", "Los niños desarrollan habilidades lingüísticas increíblemente rápido."),
                ExampleSentence("We need to develop a solid plan for our trip.", "Necesitamos desarrollar un plan sólido para nuestro viaje.")
            ),
            synonyms = listOf("Grow", "Evolve", "Expand", "Create")
        ),
        WordDefinition(
            english = "Endeavor",
            translation = "Esfuerzo / Empeño / Intentar",
            ipa = "/ɪnˈdev.ər/",
            partOfSpeech = "Sustantivo / Verbo",
            definitionEs = "Un intento serio o determinado de lograr algo difícil; esforzarse intensamente.",
            definitionEn = "An attempt to achieve a goal; try hard to do or achieve something.",
            examples = listOf(
                ExampleSentence("We wish you success in your new artistic endeavor.", "Te deseamos éxito en tu nuevo esfuerzo artístico."),
                ExampleSentence("They endeavored to complete the bridge on time.", "Se esforzaron por terminar el puente a tiempo.")
            ),
            synonyms = listOf("Attempt", "Effort", "Strive", "Undertaking")
        ),
        WordDefinition(
            english = "Flawless",
            translation = "Impecable / Perfecto",
            ipa = "/ˈflɔː.ləs/",
            partOfSpeech = "Adjetivo",
            definitionEs = "Que no tiene ninguna falta o imperfección; absolutamente perfecto.",
            definitionEn = "Without any blemishes or imperfections; perfect.",
            examples = listOf(
                ExampleSentence("Her English pronunciation was flawless.", "Su pronunciación de inglés fue impecable."),
                ExampleSentence("The diamond was completely flawless.", "El diamante era completamente perfecto.")
            ),
            synonyms = listOf("Perfect", "Impeccable", "Spotless", "Faultless")
        ),
        WordDefinition(
            english = "Gather",
            translation = "Reunir / Recolectar / Juntar",
            ipa = "/ˈɡæð.ər/",
            partOfSpeech = "Verbo",
            definitionEs = "Reunirse en un lugar o recolectar cosas dispersas para formar un conjunto.",
            definitionEn = "Come together or bring together from different places or sources.",
            examples = listOf(
                ExampleSentence("A crowd gathered to listen to the musician.", "Una multitud se reunió para escuchar al músico."),
                ExampleSentence("He gathered his books and left the library.", "Él juntó sus libros y se fue de la biblioteca.")
            ),
            synonyms = listOf("Assemble", "Collect", "Accumulate", "Meet")
        ),
        WordDefinition(
            english = "Humble",
            translation = "Humilde / Modesto",
            ipa = "/ˈhʌm.bəl/",
            partOfSpeech = "Adjetivo / Verbo",
            definitionEs = "Que tiene una actitud modesta, no orgullosa; de origen sencillo.",
            definitionEn = "Having or showing a modest or low estimate of one's own importance.",
            examples = listOf(
                ExampleSentence("Despite his fortune, he remains extremely humble.", "A pesar de su fortuna, sigue siendo extremadamente humilde."),
                ExampleSentence("The family lived in a humble cottage near the lake.", "La familia vivía en una cabaña humilde cerca del lago.")
            ),
            synonyms = listOf("Modest", "Meek", "Simple", "Unpretentious")
        ),
        WordDefinition(
            english = "Improve",
            translation = "Mejorar / Perfeccionar",
            ipa = "/ɪmˈpruːv/",
            partOfSpeech = "Verbo",
            definitionEs = "Hacer que algo sea mejor, de mayor calidad o más eficiente de lo que era.",
            definitionEn = "Make or become better in quality, condition, or performance.",
            examples = listOf(
                ExampleSentence("You can improve your English by practicing daily.", "Puedes mejorar tu inglés practicando diariamente."),
                ExampleSentence("The weather is expected to improve tomorrow.", "Se espera que el clima mejore mañana.")
            ),
            synonyms = listOf("Enhance", "Ameliorate", "Better", "Refine")
        ),
        WordDefinition(
            english = "Jeopardize",
            translation = "Poner en peligro / Arriesgar",
            ipa = "/ˈdʒep.ə.daɪz/",
            partOfSpeech = "Verbo",
            definitionEs = "Poner a alguien o algo en una situación en la que hay riesgo de pérdida, daño o fracaso.",
            definitionEn = "Put someone or something into a situation in which there is a danger of loss, harm, or failure.",
            examples = listOf(
                ExampleSentence("Don't jeopardize your career by breaking the rules.", "No pongas en peligro tu carrera rompiendo las reglas."),
                ExampleSentence("Bad communication can jeopardize the whole project.", "La mala comunicación puede poner en peligro todo el proyecto.")
            ),
            synonyms = listOf("Endanger", "Risk", "Threaten", "Compromise")
        ),
        WordDefinition(
            english = "Knowledge",
            translation = "Conocimiento / Saber",
            ipa = "/ˈnɒl.ɪdʒ/",
            partOfSpeech = "Sustantivo",
            definitionEs = "Conjunto de información, habilidades y comprensión adquiridas a través de la experiencia o la educación.",
            definitionEn = "Facts, information, and skills acquired by a person through experience or education.",
            examples = listOf(
                ExampleSentence("She has an extensive knowledge of English grammar.", "Ella tiene un conocimiento extenso de la gramática inglesa."),
                ExampleSentence("Knowledge is power.", "El conocimiento es poder.")
            ),
            synonyms = listOf("Understanding", "Wisdom", "Information", "Lore")
        ),
        WordDefinition(
            english = "Leisure",
            translation = "Ocio / Tiempo libre",
            ipa = "/ˈleʒ.ər/",
            partOfSpeech = "Sustantivo",
            definitionEs = "Tiempo libre en el que no se trabaja y se puede usar para el descanso, deporte o placer.",
            definitionEn = "Time when one is free from work or other duties; ease or relaxation.",
            examples = listOf(
                ExampleSentence("I enjoy reading novels in my leisure time.", "Disfruto leer novelas en mi tiempo libre."),
                ExampleSentence("The resort offer various leisure activities.", "El complejo ofrece varias actividades de ocio.")
            ),
            synonyms = listOf("Free time", "Relaxation", "Recreation", "Ease")
        ),
        WordDefinition(
            english = "Meaningful",
            translation = "Significativo / Con sentido",
            ipa = "/ˈmiː.nɪŋ.fəl/",
            partOfSpeech = "Adjetivo",
            definitionEs = "Que tiene un significado profundo, valor, importancia o propósito claro.",
            definitionEn = "Having a serious, important, or useful quality or purpose.",
            examples = listOf(
                ExampleSentence("They had a meaningful conversation about the future.", "Tuvieron una conversación significativa sobre el futuro."),
                ExampleSentence("I want to do meaningful work that helps others.", "Quiero hacer un trabajo significativo que ayude a otros.")
            ),
            synonyms = listOf("Significant", "Important", "Substantial", "Expressive")
        ),
        WordDefinition(
            english = "Overcome",
            translation = "Superar / Vencer",
            ipa = "/ˌəʊ.vəˈkʌm/",
            partOfSpeech = "Verbo",
            definitionEs = "Tener éxito al enfrentar, lidiar con o derrotar un problema, obstáculo o debilidad.",
            definitionEn = "Succeed in dealing with a problem, difficulty, or obstacle.",
            examples = listOf(
                ExampleSentence("She managed to overcome her fear of public speaking.", "Ella logró superar su miedo a hablar en público."),
                ExampleSentence("We must work together to overcome these challenges.", "Debemos trabajar juntos para superar estos desafíos.")
            ),
            synonyms = listOf("Conquer", "Surpass", "Defeat", "Vanquish")
        ),
        WordDefinition(
            english = "Resilient",
            translation = "Resiliente / Resistente",
            ipa = "/rɪˈzɪl.jənt/",
            partOfSpeech = "Adjetivo",
            definitionEs = "Capaz de recuperarse rápidamente de dificultades, traumas o situaciones adversas.",
            definitionEn = "Able to withstand or recover quickly from difficult conditions or adversity.",
            examples = listOf(
                ExampleSentence("She is a resilient woman who rebuilt her life after the storm.", "Ella es una mujer resiliente que reconstruyó su vida después de la tormenta."),
                ExampleSentence("The economy proved to be highly resilient.", "La economía demostró ser sumamente resistente.")
            ),
            synonyms = listOf("Tough", "Strong", "Elastic", "Durable")
        ),
        WordDefinition(
            english = "Scarcity",
            translation = "Escasez / Carencia",
            ipa = "/ˈskeə.sə.ti/",
            partOfSpeech = "Sustantivo",
            definitionEs = "Estado de estar corto de suministros; insuficiencia de recursos ante la demanda.",
            definitionEn = "The state of being scarce or in short supply; shortage.",
            examples = listOf(
                ExampleSentence("Water scarcity is a critical global issue.", "La escasez de agua es un problema global crítico."),
                ExampleSentence("The scarcity of skilled workers raised wages.", "La escasez de trabajadores calificados aumentó los salarios.")
            ),
            synonyms = listOf("Shortage", "Lack", "Dearth", "Deficit")
        ),
        WordDefinition(
            english = "Thrive",
            translation = "Prosperar / Crecer sanamente",
            ipa = "/θraɪv/",
            partOfSpeech = "Verbo",
            definitionEs = "Desarrollarse muy bien, con fuerza, vigor o éxito comercial y económico.",
            definitionEn = "Grow or develop well or vigorously; flourish and succeed.",
            examples = listOf(
                ExampleSentence("The flowers thrive in sunny, moist soil.", "Las flores prosperan en tierra húmeda y soleada."),
                ExampleSentence("His local business is thriving despite the crisis.", "Su negocio local está prosperando a pesar de la crisis.")
            ),
            synonyms = listOf("Flourish", "Prosper", "Succeed", "Bloom")
        ),
        WordDefinition(
            english = "Vulnerable",
            translation = "Vulnerable / Indefenso",
            ipa = "/ˈvʌl.nər.ə.bəl/",
            partOfSpeech = "Adjetivo",
            definitionEs = "Expuesto, propenso o susceptible a ser herido física o emocionalmente, o atacado.",
            definitionEn = "Susceptible to physical or emotional attack, harm, or damage.",
            examples = listOf(
                ExampleSentence("Older people are more vulnerable to winter diseases.", "Las personas mayores son más vulnerables a las enfermedades de invierno."),
                ExampleSentence("He felt vulnerable opening up about his past.", "Se sintió vulnerable al abrirse sobre su pasado.")
            ),
            synonyms = listOf("Exposed", "Defenseless", "Susceptible", "Weak")
        )
    )
}
