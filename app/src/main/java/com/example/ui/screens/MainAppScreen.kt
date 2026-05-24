package com.example.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import com.example.ui.viewmodel.LearnViewModel

enum class LearnTab(val label: String) {
    DICTIONARY("Diccionario"),
    TENSES("Tiempos"),
    PRACTICE("Práctica")
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainAppScreen(viewModel: LearnViewModel) {
    var activeTab by remember { mutableStateOf(LearnTab.DICTIONARY) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(), // Support camera punch hole margin automatically
        bottomBar = {
            NavigationBar(
                windowInsets = WindowInsets.safeDrawing, // Push navigation bar above gestures safely
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ) {
                NavigationBarItem(
                    selected = activeTab == LearnTab.DICTIONARY,
                    onClick = { activeTab = LearnTab.DICTIONARY },
                    icon = {
                        Icon(
                            imageVector = if (activeTab == LearnTab.DICTIONARY) Icons.Filled.MenuBook else Icons.Outlined.MenuBook,
                            contentDescription = "Diccionario"
                        )
                    },
                    label = { Text("Diccionario", fontWeight = FontWeight.SemiBold) },
                    modifier = Modifier.testTag("dictionary_tab")
                )

                NavigationBarItem(
                    selected = activeTab == LearnTab.TENSES,
                    onClick = { activeTab = LearnTab.TENSES },
                    icon = {
                        Icon(
                            imageVector = if (activeTab == LearnTab.TENSES) Icons.Filled.Translate else Icons.Outlined.Translate,
                            contentDescription = "Tiempos"
                        )
                    },
                    label = { Text("Tiempos", fontWeight = FontWeight.SemiBold) },
                    modifier = Modifier.testTag("tenses_tab")
                )

                NavigationBarItem(
                    selected = activeTab == LearnTab.PRACTICE,
                    onClick = { activeTab = LearnTab.PRACTICE },
                    icon = {
                        Icon(
                            imageVector = if (activeTab == LearnTab.PRACTICE) Icons.Filled.School else Icons.Outlined.School,
                            contentDescription = "Práctica"
                        )
                    },
                    label = { Text("Práctica", fontWeight = FontWeight.SemiBold) },
                    modifier = Modifier.testTag("practice_tab")
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            AnimatedContent(
                targetState = activeTab,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "TabTransition"
            ) { currentTab ->
                when (currentTab) {
                    LearnTab.DICTIONARY -> DictionaryScreen(viewModel = viewModel)
                    LearnTab.TENSES -> TensesScreen(viewModel = viewModel)
                    LearnTab.PRACTICE -> PracticeScreen(viewModel = viewModel)
                }
            }
        }
    }
}
