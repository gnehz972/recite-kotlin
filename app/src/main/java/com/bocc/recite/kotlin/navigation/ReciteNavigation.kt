package com.bocc.recite.kotlin.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.bocc.recite.kotlin.ui.screens.*
import kotlinx.serialization.Serializable

sealed interface Screen : NavKey {
    val title: String
    val icon: ImageVector
    
    @Serializable
    data object Home : Screen {
        override val title = "Home"
        override val icon = Icons.Filled.Home
    }
    
    @Serializable
    data object Book : Screen {
        override val title = "Book"
        override val icon = Icons.Filled.Book
    }
    
    @Serializable
    data object Remember : Screen {
        override val title = "Remember"
        override val icon = Icons.Filled.Psychology
    }
    
    @Serializable
    data object Settings : Screen {
        override val title = "Settings"
        override val icon = Icons.Filled.Settings
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReciteNavigation(
    navigationViewModel: NavigationViewModel = viewModel()
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                listOf(
                    Screen.Home,
                    Screen.Book,
                    Screen.Remember,
                    Screen.Settings
                ).forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = navigationViewModel.isCurrentScreen(screen),
                        onClick = {
                            navigationViewModel.navigateTo(screen)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavDisplay(
            backStack = navigationViewModel.backStack,
            modifier = Modifier.padding(innerPadding),
            transitionSpec = {
                fadeIn(tween(300)) togetherWith fadeOut(tween(300))
            },
            entryDecorators = listOf(
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),
            ),
            entryProvider = entryProvider {
                entry<Screen.Home> {
                    HomeScreen()
                }
                entry<Screen.Book> {
                    BookScreen()
                }
                entry<Screen.Remember> {
                    RememberScreen()
                }
                entry<Screen.Settings> {
                    SettingScreen()
                }
            }
        )
    }
}
