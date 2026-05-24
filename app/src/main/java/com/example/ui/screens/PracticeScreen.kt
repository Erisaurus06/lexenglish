package com.example.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.PracticeCategory
import com.example.data.PracticeData
import com.example.data.PracticePhrase
import com.example.data.PracticeVocab
import com.example.data.QuizQuestion
import com.example.ui.viewmodel.ChatMessage
import com.example.ui.viewmodel.LearnViewModel

@Composable
fun PracticeScreen(viewModel: LearnViewModel) {
    val selectedCategory by viewModel.selectedPracticeCategory.collectAsState()

    AnimatedContent(
        targetState = selectedCategory,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        label = "ContenidoPractica"
    ) { category ->
        if (category == null) {
            PracticeSelector(onCategorySelect = { viewModel.selectPracticeCategory(it) })
        } else {
            ActivePracticeSession(category = category, viewModel = viewModel)
        }
    }
}

// --- SELECTOR SCREEN FOR TOPICS ---
@Composable
fun PracticeSelector(onCategorySelect: (PracticeCategory) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(20.dp)
        ) {
            Column {
                Text(
                    text = "Práctica Situacional",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
                Text(
                    text = "Elige un escenario real para practicar vocabulario dinámico, frases esenciales, pruebas evaluativas y conversar libremente con tu Tutor IA de bolsillo.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Selecciona un tema para empezar:",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(PracticeData.categories) { category ->
                Card(
                    onClick = { onCategorySelect(category) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(54.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.secondaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(category.iconEmoji, fontSize = 28.sp)
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = category.nameEs,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = category.nameEn,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = category.descriptionEs,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

// --- ACTIVE SESSION WORKSPACE SCREEN ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivePracticeSession(category: PracticeCategory, viewModel: LearnViewModel) {
    val activeTab by viewModel.practiceTab.collectAsState()
    val tabs = listOf("Vocabulario", "Frases", "Quiz", "Tutor IA 💬")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // App bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.selectPracticeCategory(null) },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(50))
                    .size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = category.nameEs,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Práctica • ${category.nameEn}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Secondary Tabs Row (M3)
        SecondaryTabRow(
            selectedTabIndex = tabs.indexOf(activeTab)
        ) {
            tabs.forEach { tab ->
                Tab(
                    selected = activeTab == tab,
                    onClick = { viewModel.setPracticeTab(tab) },
                    text = { Text(tab, fontSize = 13.sp, fontWeight = FontWeight.SemiBold) }
                )
            }
        }

        // Display current workspace tab layout
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            when (activeTab) {
                "Vocabulario" -> VocabularyTab(category = category, viewModel = viewModel)
                "Frases" -> PhrasesTab(category = category, viewModel = viewModel)
                "Quiz" -> QuizTab(category = category, viewModel = viewModel)
                else -> ChatTutorTab(category = category, viewModel = viewModel)
            }
        }
    }
}

// ======================= WORKSPACE TAB 1: FLASHCARDS & VOCABULARY =======================
@Composable
fun VocabularyTab(category: PracticeCategory, viewModel: LearnViewModel) {
    val currentIndex by viewModel.flashcardIndex.collectAsState()
    val isFlipped by viewModel.isFlashcardFlipped.collectAsState()
    val vocabList = category.vocabulary

    if (vocabList.isEmpty()) return

    val item = vocabList[currentIndex]

    // Flashcard Flip visual animation details
    val rotationY by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = spring(dampingRatio = 0.75f),
        label = "GiroFlashcard"
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Fichas de Estudio (Flashcards)",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 2.dp)
        )
        Text(
            text = "Toca la tarjeta para voltearla y ver la traducción, luego repite en voz alta.",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // The Flashcard Frame
        Card(
            onClick = { viewModel.flipFlashcard() },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .graphicsLayer {
                    this.rotationY = rotationY
                    this.cameraDistance = 12f * density
                },
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isFlipped) {
                    MaterialTheme.colorScheme.secondaryContainer
                } else {
                    MaterialTheme.colorScheme.primaryContainer
                }
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        // Keep text upright when card is upside-down due to 180deg flip!
                        if (rotationY > 90f) {
                            this.rotationY = 180f
                        }
                    }
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                if (rotationY <= 90f) {
                    // Front Face: English word
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = item.english,
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = item.phonetic,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        IconButton(
                            onClick = { 
                                viewModel.speakText(item.english)
                            },
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(50))
                                .size(40.dp)
                        ) {
                            Icon(
                                Icons.Default.VolumeUp,
                                contentDescription = "Pronunciar",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                } else {
                    // Back Face: Spanish definition and example
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = item.spanish,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Ejemplo: ${item.example}",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "(${item.exampleTranslation})",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f),
                            textAlign = TextAlign.Center,
                            lineHeight = 14.sp,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }
            }
        }

        // Flashcard traversal controls
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.prevFlashcard(vocabList.size) },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(50))
                    .size(44.dp)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Anterior", tint = MaterialTheme.colorScheme.primary)
            }
            Text(
                text = "${currentIndex + 1} de ${vocabList.size}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            IconButton(
                onClick = { viewModel.nextFlashcard(vocabList.size) },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(50))
                    .size(44.dp)
            ) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Siguiente", tint = MaterialTheme.colorScheme.primary)
            }
        }

        // Quick Scroll vocabulary review below
        Text(
            text = "Glosario Completo",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(vocabList) { index, word ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (index == currentIndex) {
                                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                            } else {
                                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f)
                            }
                        )
                        .clickable { viewModel.nextFlashcard(vocabList.size); viewModel.prevFlashcard(vocabList.size); /* Reset and point to exact */ }
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { viewModel.speakText(word.english) },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(Icons.Default.VolumeUp, contentDescription = "Pronunciar", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(word.english, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        Text(word.spanish, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
    }
}

// ======================= WORKSPACE TAB 2: PHRASES =======================
@Composable
fun PhrasesTab(category: PracticeCategory, viewModel: LearnViewModel) {
    val phrases = category.phrases

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Frases de Conversación Práctica",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 2.dp)
        )
        Text(
            text = "Usa estas expresiones esenciales durante tus interacciones. Presiona el parlante para ensayar la expresión.",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(phrases) { phrase ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { viewModel.speakText(phrase.english) },
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(50))
                                .size(40.dp)
                        ) {
                            Icon(Icons.Default.VolumeUp, contentDescription = "Pronunciar", tint = MaterialTheme.colorScheme.primary)
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = phrase.english,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = phrase.spanish,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(MaterialTheme.colorScheme.secondaryContainer)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = phrase.situation,
                                    fontSize = 10.sp,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// ======================= WORKSPACE TAB 3: QUIZ =======================
@Composable
fun QuizTab(category: PracticeCategory, viewModel: LearnViewModel) {
    val currentIndex by viewModel.currentQuizIndex.collectAsState()
    val score by viewModel.quizScore.collectAsState()
    val isFinished by viewModel.isQuizFinished.collectAsState()
    val selectedIndex by viewModel.selectedAnswerIndex.collectAsState()
    val quizQuestions = category.quiz

    if (quizQuestions.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No hay preguntas disponibles para esta categoría.")
        }
        return
    }

    if (isFinished) {
        // Quiz report card screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Default.School,
                contentDescription = "Certificado",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(72.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "¡Prueba Completada!",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Has acumulado:",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
            Text(
                text = "$score de ${quizQuestions.size} respuestas",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            val rating = when {
                score == quizQuestions.size -> "¡Perfecto! Dominas este vocabulario al 100% 🏆"
                score >= quizQuestions.size / 2 -> "¡Muy bien! Sigue practicando para alcanzar la perfección. 📚"
                else -> "¡Buen intento! Repasa las fichas y vuelve a intentarlo. 💪"
            }

            Text(
                text = rating,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = { viewModel.resetQuiz() },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Icon(Icons.Default.Refresh, contentDescription = "Reiniciar")
                Spacer(modifier = Modifier.width(6.dp))
                Text("Reintentar Prueba", fontSize = 14.sp)
            }
        }
    } else {
        // Core interactive quiz interface
        val question = quizQuestions[currentIndex]

        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Prueba de Aprendizaje",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Puntaje: $score / ${quizQuestions.size}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            // Progress Bar
            val progress = (currentIndex.toFloat() + 1f) / quizQuestions.size.toFloat()
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.primaryContainer
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Evaluative Question Box
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = question.questionEs,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = question.questionEn,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Multi Choice Options list
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                question.options.forEachIndexed { optIndex, text ->
                    val isAnswered = selectedIndex != -1
                    val isCurrentSelected = selectedIndex == optIndex
                    val isCorrect = optIndex == question.correctIndex

                    // Determine background colors for option card dynamically
                    val containerColor by animateColorAsState(
                        targetValue = when {
                            isAnswered && isCorrect -> Color(0xFFE8F5E9) // Correct option green glow
                            isAnswered && isCurrentSelected && !isCorrect -> Color(0xFFFFEBEE) // Incorrect selected color red glow
                            isCurrentSelected -> MaterialTheme.colorScheme.primaryContainer
                            else -> MaterialTheme.colorScheme.surface
                        },
                        label = "FondoOpcionQuiz"
                    )

                    val borderThickness = if (isCurrentSelected || (isAnswered && isCorrect)) 2.dp else 1.dp
                    val borderColor = when {
                        isAnswered && isCorrect -> Color(0xFF2E7D32)
                        isAnswered && isCurrentSelected && !isCorrect -> Color(0xFFC62828)
                        isCurrentSelected -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                    }

                    Card(
                        onClick = { viewModel.submitAnswer(optIndex, question) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .graphicsLayer {
                                if (isAnswered) shadowElevation = 0f
                            },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = containerColor),
                        border = androidx.compose.foundation.BorderStroke(borderThickness, borderColor)
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(
                                        when {
                                            isAnswered && isCorrect -> Color(0xFF2E7D32)
                                            isAnswered && isCurrentSelected && !isCorrect -> Color(0xFFC62828)
                                            isCurrentSelected -> MaterialTheme.colorScheme.primary
                                            else -> MaterialTheme.colorScheme.surfaceVariant
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isAnswered && isCorrect) {
                                    Icon(Icons.Default.Check, contentDescription = "Correcto", tint = Color.White, modifier = Modifier.size(12.dp))
                                } else if (isAnswered && isCurrentSelected && !isCorrect) {
                                    Icon(Icons.Default.Close, contentDescription = "Incorrecto", tint = Color.White, modifier = Modifier.size(12.dp))
                                } else {
                                    Text(
                                        text = "${'A' + optIndex}",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isCurrentSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = text,
                                fontSize = 14.sp,
                                fontWeight = if (isCurrentSelected) FontWeight.Bold else FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                // If locked feedback, display explanation detail
                if (selectedIndex != -1) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "Explicación académica:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Text(
                                text = question.explanationEs,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.85f),
                                lineHeight = 16.sp,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                    }
                }
            }

            // Next Question Button
            Button(
                onClick = { viewModel.nextQuizQuestion(quizQuestions.size) },
                enabled = selectedIndex != -1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = if (currentIndex == quizQuestions.size - 1) "Finalizar Prueba" else "Siguiente Pregunta",
                    fontSize = 14.sp
                )
            }
        }
    }
}

// ======================= WORKSPACE TAB 4: CHAT WITH AI TUTOR =======================
@Composable
fun ChatTutorTab(category: PracticeCategory, viewModel: LearnViewModel) {
    val messagesMap by viewModel.chatMessages.collectAsState()
    val chatHistory = messagesMap[category.id] ?: listOf(ChatMessage(text = category.greetingMessage, isUser = false))
    val isChatLoading by viewModel.isChatLoading.collectAsState()
    
    var userText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    // Scroll to bottom when history changes
    LaunchedEffect(chatHistory.size) {
        if (chatHistory.isNotEmpty()) {
            listState.animateScrollToItem(chatHistory.size - 1)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f)
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Guia",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Conversa en inglés con el Tutor Inteligente. Si cometes un error, ¡te ayudará amablemente en español entre paréntesis!",
                    fontSize = 11.sp,
                    lineHeight = 15.sp,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }

        // Messages Box Scroll list
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 4.dp)
        ) {
            items(chatHistory) { msg ->
                ChatBubbleRow(message = msg, onSpeakClick = { viewModel.speakText(msg.text) })
            }

            if (isChatLoading) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp),
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Tutor inteligente redactando...",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                        )
                    }
                }
            }
        }

        // Send row controls
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = userText,
                onValueChange = { userText = it },
                placeholder = { Text("Escribe tu respuesta en inglés...", fontSize = 13.sp) },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = {
                    if (userText.isNotBlank()) {
                        viewModel.sendChatMessage(userText)
                        userText = ""
                    }
                }),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = {
                    if (userText.isNotBlank()) {
                        viewModel.sendChatMessage(userText)
                        userText = ""
                    }
                },
                enabled = userText.isNotBlank(),
                modifier = Modifier
                    .background(
                        color = if (userText.isNotBlank()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(50)
                    )
                    .size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Enviar",
                    tint = if (userText.isNotBlank()) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun ChatBubbleRow(message: ChatMessage, onSpeakClick: () -> Unit) {
    val isUser = message.isUser
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        if (!isUser) {
            // Tutor bubble with pronunciation support
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                ),
                shape = RoundedCornerShape(12.dp, 12.dp, 12.dp, 0.dp),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(end = 12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    IconButton(
                        onClick = onSpeakClick,
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Pronunciar diálogo",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = message.text,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 17.sp
                    )
                }
            }
        } else {
            // Student bubble
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(12.dp, 12.dp, 0.dp, 12.dp),
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .padding(start = 12.dp)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = message.text,
                        fontSize = 13.sp,
                        color = Color.White,
                        lineHeight = 17.sp
                    )
                }
            }
        }
    }
}
