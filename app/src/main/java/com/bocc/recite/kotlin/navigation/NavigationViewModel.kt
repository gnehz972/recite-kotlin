package com.bocc.recite.kotlin.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel to manage navigation state using Navigation 3
 * Manages the backStack directly as required by Nav3
 */
@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {
    
    val backStack = mutableStateListOf<Screen>(Screen.Home)
    
    fun navigateTo(screen: Screen) {
        // Remove any existing instance of the same screen type to avoid duplicates
        backStack.removeAll { it::class == screen::class }
        backStack.add(screen)
    }
    
    fun navigateBack() {
        if (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }
    
    fun navigateToHome() {
        backStack.clear()
        backStack.add(Screen.Home)
    }
    
    fun isCurrentScreen(screen: Screen): Boolean {
        return backStack.lastOrNull() == screen
    }
}
