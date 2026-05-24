package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.DictionaryData
import com.example.data.PracticeCategory
import com.example.data.PracticeData
import com.example.data.QuizQuestion
import com.example.data.TensesData
import com.example.data.VerbTense
import com.example.data.WordDefinition
import com.example.network.GeminiClient
import com.example.ui.components.TextToSpeechHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ChatMessage(
    val text: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

class LearnViewModel(application: Application) : AndroidViewModel(application) {

    private val ttsHelper = TextToSpeechHelper(application)

    // --- Dictionary State ---
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _filteredWords = MutableStateFlow<List<WordDefinition>>(DictionaryData.localWords)
    val filteredWords: StateFlow<List<WordDefinition>> = _filteredWords.asStateFlow()

    private val _selectedWord = MutableStateFlow<WordDefinition?>(null)
    val selectedWord: StateFlow<WordDefinition?> = _selectedWord.asStateFlow()

    private val _isAiSearching = MutableStateFlow(false)
    val isAiSearching: StateFlow<Boolean> = _isAiSearching.asStateFlow()

    private val _aiSearchError = MutableStateFlow<String?>(null)
    val aiSearchError: StateFlow<String?> = _aiSearchError.asStateFlow()

    // --- Tenses State ---
    private val _selectedTenseCategory = MutableStateFlow("Todos")
    val selectedTenseCategory: StateFlow<String> = _selectedTenseCategory.asStateFlow()

    private val _filteredTenses = MutableStateFlow<List<VerbTense>>(TensesData.tenses)
    val filteredTenses: StateFlow<List<VerbTense>> = _filteredTenses.asStateFlow()

    private val _selectedTense = MutableStateFlow<VerbTense?>(null)
    val selectedTense: StateFlow<VerbTense?> = _selectedTense.asStateFlow()

    // --- Practice State ---
    private val _selectedPracticeCategory = MutableStateFlow<PracticeCategory?>(null)
    val selectedPracticeCategory: StateFlow<PracticeCategory?> = _selectedPracticeCategory.asStateFlow()

    private val _practiceTab = MutableStateFlow("Vocabulario") // "Vocabulario", "Frases", "Quiz", "Tutor IA"
    val practiceTab: StateFlow<String> = _practiceTab.asStateFlow()

    // Flashcards index/flipped
    private val _flashcardIndex = MutableStateFlow(0)
    val flashcardIndex: StateFlow<Int> = _flashcardIndex.asStateFlow()

    private val _isFlashcardFlipped = MutableStateFlow(false)
    val isFlashcardFlipped: StateFlow<Boolean> = _isFlashcardFlipped.asStateFlow()

    // Quiz internal state
    private val _currentQuizIndex = MutableStateFlow(0)
    val currentQuizIndex: StateFlow<Int> = _currentQuizIndex.asStateFlow()

    private val _selectedAnswerIndex = MutableStateFlow(-1)
    val selectedAnswerIndex: StateFlow<Int> = _selectedAnswerIndex.asStateFlow()

    private val _quizScore = MutableStateFlow(0)
    val quizScore: StateFlow<Int> = _quizScore.asStateFlow()

    private val _isQuizFinished = MutableStateFlow(false)
    val isQuizFinished: StateFlow<Boolean> = _isQuizFinished.asStateFlow()

    // Chat history state
    private val _chatMessages = MutableStateFlow<Map<String, List<ChatMessage>>>(emptyMap()) // CategoryID -> Message list
    val chatMessages: StateFlow<Map<String, List<ChatMessage>>> = _chatMessages.asStateFlow()

    private val _isChatLoading = MutableStateFlow(false)
    val isChatLoading: StateFlow<Boolean> = _isChatLoading.asStateFlow()


    init {
        // Initial setup
        filterWords("")
    }

    // Speak English audio
    fun speakText(text: String) {
        ttsHelper.speak(text)
    }

    // --- DICTIONARY ACTIONS ---
    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        filterWords(query)
    }

    private fun filterWords(query: String) {
        if (query.isBlank()) {
            _filteredWords.value = DictionaryData.localWords
        } else {
            _filteredWords.value = DictionaryData.localWords.filter {
                it.english.contains(query, ignoreCase = true) ||
                        it.translation.contains(query, ignoreCase = true)
            }
        }
    }

    fun selectWord(word: WordDefinition?) {
        _selectedWord.value = word
    }

    // Infinite searches fallback via Gemini
    fun lookupWordOnline(word: String) {
        if (word.isBlank()) return
        
        viewModelScope.launch {
            _isAiSearching.value = true
            _aiSearchError.value = null
            
            val result = GeminiClient.lookUpWord(word)
            _isAiSearching.value = false
            
            if (result != null) {
                // Add dynamically to local list so it feels responsive!
                val updatedLocal = _filteredWords.value.toMutableList()
                if (updatedLocal.none { it.english.equals(result.english, ignoreCase = true) }) {
                    updatedLocal.add(0, result)
                    _filteredWords.value = updatedLocal
                }
                _selectedWord.value = result
            } else {
                _aiSearchError.value = "No se pudo obtener la palabra. Revisa la conexión o configuración de GEMINI_API_KEY."
            }
        }
    }

    // --- TENSES ACTIONS ---
    fun setTenseCategory(category: String) {
        _selectedTenseCategory.value = category
        if (category == "Todos") {
            _filteredTenses.value = TensesData.tenses
        } else {
            _filteredTenses.value = TensesData.tenses.filter { it.category == category }
        }
    }

    fun selectTense(tense: VerbTense?) {
        _selectedTense.value = tense
    }

    // --- PRACTICE ACTIONS ---
    fun selectPracticeCategory(category: PracticeCategory?) {
        _selectedPracticeCategory.value = category
        _practiceTab.value = "Vocabulario"
        _flashcardIndex.value = 0
        _isFlashcardFlipped.value = false
        resetQuiz()
        
        // Populate chat greetings
        if (category != null) {
            val list = _chatMessages.value[category.id]
            if (list == null) {
                val updatedMap = _chatMessages.value.toMutableMap()
                updatedMap[category.id] = listOf(
                    ChatMessage(text = category.greetingMessage, isUser = false)
                )
                _chatMessages.value = updatedMap
            }
        }
    }

    fun setPracticeTab(tab: String) {
        _practiceTab.value = tab
    }

    // Flashcard traversal
    fun nextFlashcard(size: Int) {
        if (size == 0) return
        _isFlashcardFlipped.value = false
        _flashcardIndex.value = (_flashcardIndex.value + 1) % size
    }

    fun prevFlashcard(size: Int) {
        if (size == 0) return
        _isFlashcardFlipped.value = false
        _flashcardIndex.value = if (_flashcardIndex.value == 0) size - 1 else _flashcardIndex.value - 1
    }

    fun flipFlashcard() {
        _isFlashcardFlipped.value = !_isFlashcardFlipped.value
    }

    // Quiz logic
    fun submitAnswer(optionIndex: Int, question: QuizQuestion) {
        if (_selectedAnswerIndex.value != -1) return // Already answered
        
        _selectedAnswerIndex.value = optionIndex
        if (optionIndex == question.correctIndex) {
            _quizScore.value += 1
        }
    }

    fun nextQuizQuestion(totalQuestions: Int) {
        _selectedAnswerIndex.value = -1
        val nextIndex = _currentQuizIndex.value + 1
        if (nextIndex < totalQuestions) {
            _currentQuizIndex.value = nextIndex
        } else {
            _isQuizFinished.value = true
        }
    }

    fun resetQuiz() {
        _currentQuizIndex.value = 0
        _selectedAnswerIndex.value = -1
        _quizScore.value = 0
        _isQuizFinished.value = false
    }

    // AI Conversations Tutor
    fun sendChatMessage(message: String) {
        val category = _selectedPracticeCategory.value ?: return
        if (message.isBlank()) return

        val currentHistory = _chatMessages.value[category.id]?.toMutableList() ?: mutableListOf()
        val userMsg = ChatMessage(text = message, isUser = true)
        currentHistory.add(userMsg)

        // Update UI instantly with the student's typed text
        val updatedMap = _chatMessages.value.toMutableMap()
        updatedMap[category.id] = currentHistory
        _chatMessages.value = updatedMap

        viewModelScope.launch {
            _isChatLoading.value = true
            
            // Map state history to simple system prompt layout
            val chatHistorySimple = currentHistory.dropLast(1).map { Pair(it.text, it.isUser) }
            
            val aiResponse = GeminiClient.chatWithTutor(
                systemInstruction = category.aiTutorPrompt,
                chatHistory = chatHistorySimple,
                newMessage = message
            )
            
            _isChatLoading.value = false
            
            val botMsg = ChatMessage(text = aiResponse, isUser = false)
            val finalHistory = _chatMessages.value[category.id]?.toMutableList() ?: mutableListOf()
            finalHistory.add(botMsg)
            
            val finalMap = _chatMessages.value.toMutableMap()
            finalMap[category.id] = finalHistory
            _chatMessages.value = finalMap
        }
    }

    override fun onCleared() {
        super.onCleared()
        ttsHelper.shutdown()
    }
}
