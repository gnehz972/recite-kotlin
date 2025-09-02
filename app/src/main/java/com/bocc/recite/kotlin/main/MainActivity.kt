package com.bocc.recite.kotlin.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bocc.recite.kotlin.navigation.ReciteNavigation
import com.bocc.recite.kotlin.ui.theme.ReciteKotlinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReciteKotlinTheme {
                ReciteNavigation()
            }
        }
    }
}
