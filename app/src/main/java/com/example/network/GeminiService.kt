package com.example.network

import com.example.BuildConfig
import com.example.data.ExampleSentence
import com.example.data.WordDefinition
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

// --- Data structures for Moshi mapping of Gemini REST ---

data class GeminiRequest(
    @Json(name = "contents") val contents: List<Content>,
    @Json(name = "generationConfig") val generationConfig: GenerationConfig? = null,
    @Json(name = "systemInstruction") val systemInstruction: Content? = null
)

data class Content(
    @Json(name = "parts") val parts: List<Part>
)

data class Part(
    @Json(name = "text") val text: String
)

data class GenerationConfig(
    @Json(name = "temperature") val temperature: Float? = null,
    @Json(name = "responseMimeType") val responseMimeType: String? = null
)

data class GeminiResponse(
    @Json(name = "candidates") val candidates: List<Candidate>?
)

data class Candidate(
    @Json(name = "content") val content: Content?
)

// --- Helper class for mapping Gemini structured dictionary search response ---
data class WordInfoJson(
    @Json(name = "translation") val translation: String = "",
    @Json(name = "ipa") val ipa: String = "",
    @Json(name = "partOfSpeech") val partOfSpeech: String = "",
    @Json(name = "definitionEs") val definitionEs: String = "",
    @Json(name = "definitionEn") val definitionEn: String = "",
    @Json(name = "synonyms") val synonyms: List<String> = emptyList(),
    @Json(name = "examples") val examples: List<ExampleJson> = emptyList()
)

data class ExampleJson(
    @Json(name = "english") val english: String = "",
    @Json(name = "spanish") val spanish: String = ""
)

interface GeminiApiService {
    @POST("v1beta/models/gemini-3.5-flash:generateContent")
    suspend fun generateContent(
        @Query("key") apiKey: String,
        @Body request: GeminiRequest
    ): GeminiResponse
}

object GeminiClient {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private val apiService: GeminiApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://generativelanguage.googleapis.com/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(GeminiApiService::class.java)
    }

    // Lookup word implementation
    suspend fun lookUpWord(word: String): WordDefinition? {
        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            return null
        }

        val prompt = """
            Look up the English word "$word". Return a structured JSON response in Spanish.
            You must return only the JSON with no markdown wrapping and no backticks (or return as valid application/json).
            Format:
            {
              "translation": "español translation (one or two words)",
              "ipa": "phonetics symbols /.../",
              "partOfSpeech": "Sustantivo, Verbo, Adjetivo, etc.",
              "definitionEs": "definition in Spanish",
              "definitionEn": "definition in English",
              "synonyms": ["synonym1", "synonym2"],
              "examples": [
                 {
                   "english": "Example sentence in English using $word",
                   "spanish": "Traducción de la oración al español"
                 },
                 {
                   "english": "Another sentence in English",
                   "spanish": "Traducción al español de la segunda"
                 }
              ]
            }
        """.trimIndent()

        val request = GeminiRequest(
            contents = listOf(Content(parts = listOf(Part(text = prompt)))),
            generationConfig = GenerationConfig(
                temperature = 0.2f,
                responseMimeType = "application/json"
            )
        )

        return try {
            val response = apiService.generateContent(apiKey, request)
            val jsonText = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
            if (jsonText != null) {
                val adapter = moshi.adapter(WordInfoJson::class.java)
                val parsed = adapter.fromJson(jsonText)
                if (parsed != null) {
                    WordDefinition(
                        english = word.replaceFirstChar { it.uppercase() },
                        translation = parsed.translation,
                        ipa = parsed.ipa,
                        partOfSpeech = parsed.partOfSpeech,
                        definitionEs = parsed.definitionEs,
                        definitionEn = parsed.definitionEn,
                        synonyms = parsed.synonyms,
                        examples = parsed.examples.map { ExampleSentence(it.english, it.spanish) }
                    )
                } else null
            } else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // Call chat tutor implementation
    suspend fun chatWithTutor(
        systemInstruction: String,
        chatHistory: List<Pair<String, Boolean>>, // Pair of (Message content, isUserMessage)
        newMessage: String
    ): String {
        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            return "Lo siento, la API Key de Gemini no está configurada en AI Studio. Por favor, configura tu GEMINI_API_KEY en el panel de secretos."
        }

        val requestContents = mutableListOf<Content>()
        
        // Add conversational history to maintain state
        chatHistory.forEach { (text, isUser) ->
            requestContents.add(Content(parts = listOf(Part(text = text))))
        }
        
        // Add new student request/reply
        requestContents.add(Content(parts = listOf(Part(text = newMessage))))

        val request = GeminiRequest(
            contents = requestContents,
            systemInstruction = Content(parts = listOf(Part(text = systemInstruction))),
            generationConfig = GenerationConfig(temperature = 0.7f)
        )

        return try {
            val response = apiService.generateContent(apiKey, request)
            response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
                ?: "No response from AI Tutor."
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }
}
