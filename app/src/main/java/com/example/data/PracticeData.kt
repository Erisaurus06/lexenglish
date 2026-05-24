package com.example.data

data class PracticeCategory(
    val id: String,
    val nameEs: String,
    val nameEn: String,
    val iconEmoji: String, // Decorative visual element
    val descriptionEs: String,
    val vocabulary: List<PracticeVocab>,
    val phrases: List<PracticePhrase>,
    val quiz: List<QuizQuestion>,
    val aiTutorPrompt: String,
    val greetingMessage: String
)

data class PracticeVocab(
    val english: String,
    val spanish: String,
    val phonetic: String,
    val example: String,
    val exampleTranslation: String
)

data class PracticePhrase(
    val english: String,
    val spanish: String,
    val situation: String
)

data class QuizQuestion(
    val questionEs: String,
    val questionEn: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanationEs: String
)

object PracticeData {
    val categories = listOf(
        PracticeCategory(
            id = "restaurant",
            nameEs = "Restaurante",
            nameEn = "At the Restaurant",
            iconEmoji = "🍽️",
            descriptionEs = "Aprende a ordenar comida, pedir la cuenta, hacer preguntas sobre el menú y comunicarte con el mesero.",
            greetingMessage = "Hello! I am your waiter today. Welcome to 'The Golden Grill'. Are you ready to order, or do you need a few minutes?",
            aiTutorPrompt = """
                You are a very polite waiter at a nice American restaurant called "The Golden Grill". 
                The user is a student practicing restaurant English.
                Respond to the user's messages in character, as a waiter. Keep your responses short (1-2 sentences in English).
                If the user makes any grammatical mistakes, gently offer a correction in Spanish in parentheses at the end of your response, e.g., "(Nota: Se dice 'I would like a glass' en lugar de 'I want check'.)".
                Keep the tone warm, welcoming, and encouraging.
            """.trimIndent(),
            vocabulary = listOf(
                PracticeVocab("Waiter / Waitress", "Mesero / Mesera", "/ˈweɪ.tər/ - /ˈweɪ.trəs/", "The waiter brought us some water.", "El mesero nos trajo un poco de agua."),
                PracticeVocab("Menu", "Menú / Carta", "/ˈmen.juː/", "Could we see the menu, please?", "¿Podríamos ver la carta, por favor?"),
                PracticeVocab("Appetizer", "Entrada / Aperitivo", "/ˈæp.ə.taɪ.zər/", "I will order the garlic bread as an appetizer.", "Pediré el pan de ajo como entrada."),
                PracticeVocab("Main course", "Plato fuerte / principal", "/meɪn kɔːs/", "For my main course, I would like the steak.", "Como plato principal, me gustaría el bistec."),
                PracticeVocab("Dessert", "Postre", "/dɪˈzɜːt/", "Do you have any chocolate cake for dessert?", "¿Tienen pastel de chocolate para el postre?"),
                PracticeVocab("Bill / Check", "La cuenta", "/bɪl/ - /tʃek/", "Excuse me, we are ready for the bill.", "Disculpe, estamos listos para la cuenta.")
            ),
            phrases = listOf(
                PracticePhrase("A table for two, please.", "Una mesa para dos, por favor.", "Llegando al restaurante"),
                PracticePhrase("Could we get some water?", "¿Nos podría traer agua?", "Durante la comida"),
                PracticePhrase("What do you recommend?", "¿Qué nos recomienda?", "Ordenando comida"),
                PracticePhrase("I am allergic to nuts.", "Soy alérgico/a a las nueces.", "Ordenando comida"),
                PracticePhrase("Keep the change.", "Quédese con el cambio.", "Pagando la cuenta")
            ),
            quiz = listOf(
                QuizQuestion(
                    questionEs = "¿Cómo pedirías la cuenta amablemente al mesero?",
                    questionEn = "How do you ask for the check politely?",
                    options = listOf(
                        "Give me the money now.",
                        "Could we have the bill, please?",
                        "I want pay today.",
                        "Hey, come here and bill."
                    ),
                    correctIndex = 1,
                    explanationEs = "'Could we have the bill, please?' es la forma más educada y estándar de pedir la cuenta en un restaurante de habla inglesa."
                ),
                QuizQuestion(
                    questionEs = "Si deseas ordenar comida de entrada, ¿qué palabra buscas en el menú?",
                    questionEn = "If you want a small dish before the main food, you look for:",
                    options = listOf(
                        "Dessert",
                        "Appetizers / Starters",
                        "Main Course",
                        "Beverages"
                    ),
                    correctIndex = 1,
                    explanationEs = "'Appetizers' o 'Starters' son los términos en inglés para las entradas o platos pequeños antes de la comida fuerte."
                ),
                QuizQuestion(
                    questionEs = "¿Qué expresión usas cuando quieres que el mesero se quede con el vuelto?",
                    questionEn = "What do you say to let the waiter keep the extra money as a tip?",
                    options = listOf(
                        "Take the cash.",
                        "No money back.",
                        "Keep the change.",
                        "Store my coins."
                    ),
                    correctIndex = 2,
                    explanationEs = "'Keep the change' es la frase clásica en inglés que significa literal 'quédate con el cambio' para dar propinas."
                )
            )
        ),
        PracticeCategory(
            id = "travel",
            nameEs = "Viajes",
            nameEn = "Travel & Transportation",
            iconEmoji = "✈️",
            descriptionEs = "Vocabulario clave para aeropuertos, estaciones de tren, pedir direcciones y tomar taxis.",
            greetingMessage = "Hello traveller! Welcome to JFK Airport Boarding desk. Where are you flying to today, and may I see your passport and ticket?",
            aiTutorPrompt = """
                You are a professional border agent / boarding officer at airport baggage drop-off.
                The user is a traveler.
                Respond in character. Keep responses brief (1-2 sentences in English).
                If they make mistakes, add brief Spanish corrections in parentheses at the end.
                Ask them traveler-related questions.
            """.trimIndent(),
            vocabulary = listOf(
                PracticeVocab("Boarding pass", "Pase de abordar", "/ˈbɔː.dɪŋ ˌpɑːs/", "Please show your boarding pass at gate 4.", "Por favor, muestre su pase de abordar en la puerta 4."),
                PracticeVocab("Luggage / Baggage", "Equipaje", "/ˈlʌɡ.ɪdʒ/", "I have two suitcases of checked luggage.", "Tengo dos maletas de equipaje documentado."),
                PracticeVocab("Gate", "Puerta de embarque", "/ɡeɪt/", "The flight departs from boarding gate 12.", "El vuelo sale de la puerta de embarque 12."),
                PracticeVocab("Delay", "Demora / Retraso", "/dɪˈleɪ/", "Our train has a twenty-minute delay.", "Nuestro tren tiene una demora de veinte minutos."),
                PracticeVocab("Customs", "Aduana", "/ˈkʌs.təmz/", "We had to go through customs when we arrived.", "Tuvimos que pasar por la aduana cuando llegamos.")
            ),
            phrases = listOf(
                PracticePhrase("Where is the departure gate?", "¿Dónde está la puerta de salida?", "En el aeropuerto"),
                PracticePhrase("I would like a window seat.", "Me gustaría un asiento de ventana.", "Check-in de vuelo"),
                PracticePhrase("Is this train bound for Chicago?", "¿Este tren va hacia Chicago?", "En la estación"),
                PracticePhrase("How much is the fare?", "¿De cuánto es la tarifa / costo?", "Tomando un taxi"),
                PracticePhrase("I am lost. Can you help me?", "Estoy perdido/a. ¿Me puede ayudar?", "En la ciudad")
            ),
            quiz = listOf(
                QuizQuestion(
                    questionEs = "¿Qué documento necesitas para abordar el avión una vez que documentas?",
                    questionEn = "What document lists your seat number and gate for boarding?",
                    options = listOf(
                        "Passport book",
                        "Boarding pass",
                        "Drivers license",
                        "Luggage tag"
                    ),
                    correctIndex = 1,
                    explanationEs = "El 'Boarding pass' (pase de abordar) es la tarjeta que te permite subir al avión y detalla tu asiento y puerta."
                ),
                QuizQuestion(
                    questionEs = "Si tu vuelo de conexión se cancela o aplaza, ¿cómo se le llama a esa demora?",
                    questionEn = "A situation where a flight or train is late is called:",
                    options = listOf(
                        "A rush hour",
                        "A schedule",
                        "A delay",
                        "A runway"
                    ),
                    correctIndex = 2,
                    explanationEs = "'Delay' representa un retraso o postergación en la hora programada de salida."
                )
            )
        ),
        PracticeCategory(
            id = "hotel",
            nameEs = "Hotel",
            nameEn = "At the Hotel",
            iconEmoji = "🏨",
            descriptionEs = "Práctica esencial para hacer el check-in, solicitar toallas, pedir sugerencias al recepcionista y hacer el check-out.",
            greetingMessage = "Welcome to 'Grand View Hotel'! My name is Sarah. Do you have a reservation under your name, or would you like to book a room?",
            aiTutorPrompt = """
                You are Sarah, a professional, super helpful receptionist at the 'Grand View Hotel'.
                The user wants to check-in, request room services, or check out.
                Respond in character. Keep responses brief (1-2 sentences in English).
                If they make mistakes, add brief Spanish corrections in parentheses at the end.
            """.trimIndent(),
            vocabulary = listOf(
                PracticeVocab("Reservation", "Reservación", "/ˌrez.əˈveɪ.ʃən/", "I made a reservation for three nights.", "Hice una reservación por tres noches."),
                PracticeVocab("Check-in", "Registrarse (entrada)", "/tʃek.ɪn/", "What time is checkout and check-in?", "¿A qué hora es el registro de entrada y salida?"),
                PracticeVocab("Room key", "Llave de la habitación", "/ruːm kiː/", "Here is your electronic room key.", "Aquí tiene su llave electrónica de la habitación."),
                PracticeVocab("Amenities", "Servicios / Comodidades", "/əˈmiː.nə.tiz/", "The hotel offers free breakfast and wifi as amenities.", "El hotel ofrece desayuno y wifi gratis como servicios."),
                PracticeVocab("Front desk", "Recepción", "/frʌnt desk/", "You can call the front desk if you need fresh towels.", "Puede llamar a la recepción si necesita toallas limpias.")
            ),
            phrases = listOf(
                PracticePhrase("I have a reservation under my name.", "Tengo una reservación a mi nombre.", "Ingresando al Hotel"),
                PracticePhrase("Is breakfast included?", "¿El desayuno está incluido?", "En la recepción"),
                PracticePhrase("Could I have extra pillows?", "¿Podría darme almohadas adicionales?", "Servicio al cuarto"),
                PracticePhrase("What is the Wi-Fi password?", "¿Cuál es la contraseña del Wi-Fi?", "Comodidades"),
                PracticePhrase("I would like to check-out.", "Me gustaría realizar la salida del hotel.", "Saliendo")
            ),
            quiz = listOf(
                QuizQuestion(
                    questionEs = "¿A dónde debes dirigirte para solicitar servicios u obtener tu llave?",
                    questionEn = "Where is the physical place to check in at a hotel?",
                    options = listOf(
                        "The pool",
                        "The elevator",
                        "The front desk",
                        "The restaurant"
                    ),
                    correctIndex = 2,
                    explanationEs = "'The front desk' (recepción) es la oficina física donde te atienden para darte llaves o resolver dudas en un hotel."
                ),
                QuizQuestion(
                    questionEs = "¿Qué frase usarías al dejar el hotel al concluir tus vacaciones?",
                    questionEn = "What do you say when you finish your stay and pay the total bill?",
                    options = listOf(
                        "I want to check-in.",
                        "I would like to check-out.",
                        "Show me the room.",
                        "This hotel is done."
                    ),
                    correctIndex = 1,
                    explanationEs = "'Check-out' es la acción formal de registrar tu salida del hotel."
                )
            )
        ),
        PracticeCategory(
            id = "home",
            nameEs = "Casa & Hogar",
            nameEn = "Home & Daily Life",
            iconEmoji = "🏠",
            descriptionEs = "Vocabulario de las partes de la casa, muebles, electrodomésticos y quehaceres cotidianos.",
            greetingMessage = "Hi roomie! I'm planning to clean up our house today. Can you help me organize the living room and kitchen?",
            aiTutorPrompt = """
                You are a friendly roommate sharing an apartment with the user.
                Talk about house chores, cleaning up, groceries, and daily life in the house.
                Respond in character. Keep responses brief (1-2 sentences in English).
                If they make mistakes, add brief Spanish corrections in parentheses at the end.
            """.trimIndent(),
            vocabulary = listOf(
                PracticeVocab("Living room", "Sala de estar", "/ˈlɪv.ɪŋ ˌruːm/", "Let's sit on the sofa in the living room.", "Sentémonos en el sofá de la sala de estar."),
                PracticeVocab("Kitchen", "Cocina", "/ˈkɪtʃ.ən/", "I cook spaghetti in the kitchen.", "Cocino espagueti en la cocina."),
                PracticeVocab("Bedroom", "Habitación / Recámara", "/ˈbed.ruːm/", "My bedroom has a big comfortable bed.", "Mi habitación tiene una cama grande y cómoda."),
                PracticeVocab("Appliances", "Electrodomésticos", "/əˈplaɪ.ən.sɪz/", "The refrigerator is our most important kitchen appliance.", "El refrigerador es nuestro electrodoméstico de cocina más importante."),
                PracticeVocab("Chores", "Quehaceres / Labores domésticas", "/tʃɔːz/", "Our weekend chores include sweeping and doing laundry.", "Nuestros quehaceres de fin de semana incluyen barrer y lavar ropa.")
            ),
            phrases = listOf(
                PracticePhrase("Could you wash the dishes?", "¿Podrías lavar los platos?", "En la cocina"),
                PracticePhrase("I need to clean my room today.", "Necesito limpiar mi cuarto hoy.", "Quehaceres"),
                PracticePhrase("Where is the trash can?", "¿Dónde está el bote de basura?", "En la cocina"),
                PracticePhrase("Turn off the lights, please.", "Apaga las luces, por favor.", "Ahorrando energía"),
                PracticePhrase("Keep the living room tidy.", "Mantén limpia y ordenada la sala.", "Convivencia")
            ),
            quiz = listOf(
                QuizQuestion(
                    questionEs = "¿Cuál es el término en inglés para lavar los platos / trastes?",
                    questionEn = "What is the phrase for cleaning plates after meals?",
                    options = listOf(
                        "Do the laundry",
                        "Sweep the floor",
                        "Wash the dishes",
                        "Make the bed"
                    ),
                    correctIndex = 2,
                    explanationEs = "'Wash the dishes' significa lavar los platos, mientras que 'do the laundry' es lavar la ropa."
                )
            )
        ),
        PracticeCategory(
            id = "family",
            nameEs = "Familia",
            nameEn = "Family & Relationships",
            iconEmoji = "👨‍👩‍👧‍👦",
            descriptionEs = "Miembros de la familia directos, familia extendida (suegros, primos) y adjetivos para describir relaciones.",
            greetingMessage = "Hello! Tell me about your family. Do you have any brothers or sisters, or are you an only child?",
            aiTutorPrompt = """
                You are an English language interviewer interested in learning about the user's family tree.
                Ask friendly questions about family members, marital status, or siblings.
                Respond in character. Keep responses brief (1-2 sentences in English).
                If they make mistakes, add brief Spanish corrections in parentheses at the end.
            """.trimIndent(),
            vocabulary = listOf(
                PracticeVocab("Siblings", "Hermanos (género neutro)", "/ˈsɪb.lɪŋz/", "I have three siblings: two brothers and one sister.", "Tengo tres hermanos: dos hermanos y una hermana."),
                PracticeVocab("Parent", "Progenitor (Padre o Madre)", "/ˈpeə.rənt/", "Both of my parents are teachers.", "Ambos de mis padres son maestros."),
                PracticeVocab("Relatves", "Parientes / Familiares", "/ˈrel.ə.tɪvz/", "Our relatives gather every Christmas.", "Nuestros parientes se reúnen cada Navidad."),
                PracticeVocab("In-laws", "Familia política (suegros, etc.)", "/ˈɪn.lɔːz/", "I get along very well with my mother-in-law.", "Me llevo muy bien con mi suegra."),
                PracticeVocab("Nephew / Niece", "Sobrino / Sobrina", "/ˈnef.juː/ - /niːs/", "My niece is starting college next month.", "Mi sobrina empieza la universidad el próximo mes.")
            ),
            phrases = listOf(
                PracticePhrase("How many brothers and sisters do you have?", "¿Cuántos hermanos y hermanas tienes?", "Conociendo a alguien"),
                PracticePhrase("We are very close.", "Somos muy unidos.", "Describiendo relaciones"),
                PracticePhrase("She looks exactly like her mother.", "Ella se ve exactamente igual a su madre.", "Comparando familiares"),
                PracticePhrase("That is my cousin.", "Ese es mi primo/a.", "Presentando a la familia")
            ),
            quiz = listOf(
                QuizQuestion(
                    questionEs = "¿Qué palabra define colectivamente a tus hermanos de cualquier género de forma neutra?",
                    questionEn = "Which word refers to brothers and sisters collectively?",
                    options = listOf(
                        "Parents",
                        "Kids",
                        "Siblings",
                        "Relatives"
                    ),
                    correctIndex = 2,
                    explanationEs = "'Siblings' engloba de manera neutral a hermanos y hermanas en inglés, evitando decir 'brothers and sisters'."
                )
            )
        ),
        PracticeCategory(
            id = "food",
            nameEs = "Comida",
            nameEn = "Food & Groceries",
            iconEmoji = "🍎",
            descriptionEs = "Vocabulario de ingredientes, compras en el supermercado, recetas y diferentes sabores e ingredientes.",
            greetingMessage = "Hi chef! What is your favorite recipe to cook? Let's check our ingredients list together!",
            aiTutorPrompt = """
                You are a cheerful cooking partner helping the user in the kitchen or supermarket.
                Discuss culinary topics, recipes, ingredients, and sweet/sour flavors.
                Respond in character. Keep responses brief (1-2 sentences in English).
                If they make mistakes, add brief Spanish corrections in parentheses at the end.
            """.trimIndent(),
            vocabulary = listOf(
                PracticeVocab("Groceries", "Comestibles / Despensa", "/ˈɡrəʊ.sər.iz/", "I need to go buy groceries for the week.", "Necesito ir a comprar de comer para la semana."),
                PracticeVocab("Ingredients", "Ingredientes", "/ɪnˈɡriː.di.ənts/", "Make sure you have all the ingredients ready.", "Asegúrate de tener todos los ingredientes listos."),
                PracticeVocab("Recipe", "Receta", "/ˈres.ɪ.pi/", "This is my grandmother's secret chocolate cake recipe.", "Esta es la receta secreta del pastel de chocolate de mi abuela."),
                PracticeVocab("Flavor", "Sabor", "/ˈfleɪ.vər/", "The ice cream has a delicious vanilla flavor.", "El helado tiene un delicioso sabor a vainilla."),
                PracticeVocab("Delicious", "Delicioso", "/dɪˈlɪʃ.əs/", "The soup taste absolutely delicious.", "La sopa sabe absolutamente deliciosa.")
            ),
            phrases = listOf(
                PracticePhrase("Where can I find the produce aisle?", "¿Dónde encuentro el pasillo de frutas y verduras?", "En el supermercado"),
                PracticePhrase("Is this dish spicy?", "¿Este platillo es picante?", "Comiendo"),
                PracticePhrase("Add a pinch of salt.", "Agrega una pizca de sal.", "Cocinando una receta"),
                PracticePhrase("This tastes too sweet.", "Esto sabe demasiado dulce.", "Comentando sabores")
            ),
            quiz = listOf(
                QuizQuestion(
                    questionEs = "¿Cómo se deletrea y pronuncia la palabra 'Receta' en inglés?",
                    questionEn = "What is the English word for cooking instructions?",
                    options = listOf(
                        "Receipt",
                        "Recipe",
                        "Prescription",
                        "Receive"
                    ),
                    correctIndex = 1,
                    explanationEs = "'Recipe' (/ˈres.ɪ.pi/) es la receta de cocina. Ojo: 'receipt' significa boleto/ticket de compra y 'prescription' es receta médica."
                )
            )
        )
    )
}
