package com.example.data

data class VerbTense(
    val nameEn: String,
    val nameEs: String,
    val category: String, // "Presente", "Pasado", "Futuro"
    val description: String, // Description in Spanish
    val structureAffirmative: String,
    val structureNegative: String,
    val structureInterrogative: String,
    val rules: List<String>, // Key grammar rules/spelling rules
    val tips: String, // Helpful tips in Spanish
    val examples: List<TenseExample>
)

data class TenseExample(
    val english: String,
    val spanish: String,
    val type: String // "Affirmative", "Negative", "Interrogative"
)

object TensesData {
    val tenses = listOf(
        // --- PRESENTE ---
        VerbTense(
            nameEn = "Present Simple",
            nameEs = "Presente Simple",
            category = "Presente",
            description = "Se usa para rutinas, hechos generales, verdades universales y hábitos.",
            structureAffirmative = "Sujeto + Verbo (infinitivo + -s/-es para 3ra persona) + Complemento",
            structureNegative = "Sujeto + Do / Does + Not + Verbo (base) + Complemento",
            structureInterrogative = "Do / Does + Sujeto + Verbo (base) + Complemento + ?",
            rules = listOf(
                "Para la 3ra persona singular (He, She, It), añade -s o -es al verbo en oraciones afirmativas.",
                "Usa 'does' en tercera persona y 'do' para las demás en negaciones y preguntas.",
                "En negaciones y preguntas, el verbo principal se queda en forma base (sin -s)."
            ),
            tips = "Usa adverbios de frecuencia como: always, usually, often, sometimes, never.",
            examples = listOf(
                TenseExample("She drinks coffee every morning.", "Ella toma café todas las mañanas.", "Affirmative"),
                TenseExample("They do not play soccer on Sundays.", "Ellos no juegan fútbol los domingos.", "Negative"),
                TenseExample("Do you speak English?", "¿Tú hablas inglés?", "Interrogative")
            )
        ),
        VerbTense(
            nameEn = "Present Continuous",
            nameEs = "Presente Continuo",
            category = "Presente",
            description = "Se utiliza para acciones que están ocurriendo en el momento exacto del habla o para planes futuros fijos.",
            structureAffirmative = "Sujeto + Am/Is/Are + Verbo (-ing) + Complemento",
            structureNegative = "Sujeto + Am/Is/Are + Not + Verbo (-ing) + Complemento",
            structureInterrogative = "Am/Is/Are + Sujeto + Verbo (-ing) + Complemento + ?",
            rules = listOf(
                "Usa 'am' para I; 'is' para He, She, It; 'are' para You, We, They.",
                "Agrega '-ing' al verbo. Si el verbo termina en -e muda, elimínala (drive -> driving).",
                "Si termina en Consonante-Vocal-Consonante monosílabo, duplica la última letra (run -> running)."
            ),
            tips = "Suele ir acompañado de marcadores como: now, at the moment, right now.",
            examples = listOf(
                TenseExample("We are learning English right now.", "Estamos aprendiendo inglés justo ahora.", "Affirmative"),
                TenseExample("He is not working at the moment.", "Él no está trabajando en este momento.", "Negative"),
                TenseExample("Are they cooking dinner?", "¿Están cocinando la cena?", "Interrogative")
            )
        ),
        VerbTense(
            nameEn = "Present Perfect",
            nameEs = "Presente Perfecto",
            category = "Presente",
            description = "Conecta el pasado con el presente. Acciones que ocurrieron en un momento indeterminado o que tienen relevancia hoy.",
            structureAffirmative = "Sujeto + Have / Has + Verbo (Participio Pasado) + Complemento",
            structureNegative = "Sujeto + Have / Has + Not + Verbo (Participio Pasado) + Complemento",
            structureInterrogative = "Have / Has + Sujeto + Verbo (Participio Pasado) + Complemento + ?",
            rules = listOf(
                "Usa 'has' para He, She, It; 'have' para los demás.",
                "Para verbos regulares, añade '-ed'. Para irregulares, usa la tercera columna de verbos.",
                "No especificas cuándo ocurrió la acción exactamente."
            ),
            tips = "Suele usarse con palabras clave como: already, yet, just, playing ever, never, since, for.",
            examples = listOf(
                TenseExample("I have traveled to London twice.", "He viajado a Londres dos veces.", "Affirmative"),
                TenseExample("She has not finished her homework yet.", "Ella no ha terminado su tarea todavía.", "Negative"),
                TenseExample("Have you ever eaten sushi?", "¿Alguna vez has comido sushi?", "Interrogative")
            )
        ),

        // --- PASADO ---
        VerbTense(
            nameEn = "Past Simple",
            nameEs = "Pasado Simple",
            category = "Pasado",
            description = "Acciones que comenzaron y terminaron en un momento específico en el pasado.",
            structureAffirmative = "Sujeto + Verbo (Pasado: regular -ed / irregular) + Complemento",
            structureNegative = "Sujeto + Did + Not + Verbo (base) + Complemento",
            structureInterrogative = "Did + Sujeto + Verbo (base) + Complemento + ?",
            rules = listOf(
                "A los verbos regulares se les añade '-ed' (play -> played, study -> studied).",
                "Los verbos irregulares cambian de forma completamente (go -> went, write -> wrote).",
                "En negación y pregunta el verbo vuelve a su forma base porque 'did' ya indica pasado."
            ),
            tips = "Los marcadores de tiempo comunes son: yesterday, last week, ago, in 2020.",
            examples = listOf(
                TenseExample("They watched a movie yesterday.", "Ellos vieron una película ayer.", "Affirmative"),
                TenseExample("He did not buy the car last week.", "Él no compró el auto la semana pasada.", "Negative"),
                TenseExample("Did you sleep well?", "¿Dormiste bien?", "Interrogative")
            )
        ),
        VerbTense(
            nameEn = "Past Continuous",
            nameEs = "Pasado Continuo",
            category = "Pasado",
            description = "Acciones que estaban ocurriendo en el pasado en un momento específico o que fueron interrumpidas.",
            structureAffirmative = "Sujeto + Was / Were + Verbo (-ing) + Complemento",
            structureNegative = "Sujeto + Was / Were + Not + Verbo (-ing) + Complemento",
            structureInterrogative = "Was / Were + Sujeto + Verbo (-ing) + Complemento + ?",
            rules = listOf(
                "Usa 'was' para I, He, She, It y 'were' para You, We, They.",
                "Añade '-ing' al final del verbo principal.",
                "Se usa mucho en combinación con el Pasado Simple para interrupciones."
            ),
            tips = "Las palabras 'while' (mientras) y 'when' (cuando) indican interacción entre acciones.",
            examples = listOf(
                TenseExample("I was reading a book when she called.", "Yo estaba leyendo un libro cuando ella llamó.", "Affirmative"),
                TenseExample("They were not studying during the class.", "Ellos no estaban estudiando durante la clase.", "Negative"),
                TenseExample("Were you watching TV at 8 PM?", "¿Estabas viendo televisión a las 8 PM?", "Interrogative")
            )
        ),
        VerbTense(
            nameEn = "Past Perfect",
            nameEs = "Pasado Perfecto",
            category = "Pasado",
            description = "Acciones que ocurrieron antes de otra acción en el pasado ('el pasado del pasado').",
            structureAffirmative = "Sujeto + Had + Verbo (Participio Pasado) + Complemento",
            structureNegative = "Sujeto + Had + Not + Verbo (Participio Pasado) + Complemento",
            structureInterrogative = "Had + Sujeto + Verbo (Participio Pasado) + Complemento + ?",
            rules = listOf(
                "Usa 'had' como auxiliar obligatorio para todos los sujetos sin excepción.",
                "Acompaña con verbo regular en '-ed' o tercera columna de irregulares.",
                "Suele preceder a un verbo en Pasado Simple que marca la segunda acción posterior."
            ),
            tips = "Usa palabras como: already, before, by the time.",
            examples = listOf(
                TenseExample("The train had left when we arrived.", "El tren ya se había ido cuando llegamos.", "Affirmative"),
                TenseExample("She had not studied before the test.", "Ella no había estudiado antes del examen.", "Negative"),
                TenseExample("Had they eaten before leaving?", "¿Habían comido antes de irse?", "Interrogative")
            )
        ),

        // --- FUTURO ---
        VerbTense(
            nameEn = "Future Simple (Will)",
            nameEs = "Futuro Simple con Will",
            category = "Futuro",
            description = "Se usa para decisiones espontáneas, predicciones sin pruebas sólidas, promesas o hechos futuros.",
            structureAffirmative = "Sujeto + Will + Verbo (base) + Complemento",
            structureNegative = "Sujeto + Will + Not + Verbo (base) + Complemento",
            structureInterrogative = "Will + Sujeto + Verbo (base) + Complemento + ?",
            rules = listOf(
                "Usa el auxiliar 'will' para todos los pronombres.",
                "La contracción de 'will not' es 'won't'.",
                "El verbo principal siempre va en infinitivo sin 'to' (forma base)."
            ),
            tips = "Es muy común oírlo en promesas ('I will help you') o predicciones ('It will rain tomorrow').",
            examples = listOf(
                TenseExample("I will travel to New York next month.", "Viajaré a Nueva York el próximo mes.", "Affirmative"),
                TenseExample("They won't attend the meeting.", "Ellos no asistirán a la reunión.", "Negative"),
                TenseExample("Will you marry me?", "¿Te casarías conmigo?", "Interrogative")
            )
        ),
        VerbTense(
            nameEn = "Future Going To",
            nameEs = "Futuro de Intención (Going To)",
            category = "Futuro",
            description = "Se usa para planes previos o decisiones ya tomadas, o para predicciones basadas en evidencia física visible.",
            structureAffirmative = "Sujeto + Am / Is / Are + Going to + Verbo (base) + Complemento",
            structureNegative = "Sujeto + Am / Is / Are + Not + Going to + Verbo (base) + Complemento",
            structureInterrogative = "Am / Is / Are + Sujeto + Going to + Verbo (base) + Complemento + ?",
            rules = listOf(
                "Requiere conjugar adecuadamente el verbo 'to be' (am/is/are) según el sujeto.",
                "Sigue la estructura fija 'going to' seguida obligatoriamente por el verbo en forma base.",
                "Úsalo para intenciones premeditadas."
            ),
            tips = "Si ves nubes negras en el cielo, dices: 'It's going to rain' (evidencia), no 'will'.",
            examples = listOf(
                TenseExample("We are going to move to a new apartment.", "Nos vamos a mudar a un nuevo departamento.", "Affirmative"),
                TenseExample("He is not going to buy that house.", "Él no va a comprar esa casa.", "Negative"),
                TenseExample("Are they going to join the party?", "¿Van a unirse a la fiesta?", "Interrogative")
            )
        )
    )
}
