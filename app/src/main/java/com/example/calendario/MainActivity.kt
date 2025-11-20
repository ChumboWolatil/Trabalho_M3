package com.example.calendario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.calendario.ui.nav.NavGraph
// Import Material 3 components
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // It's good practice to define a theme for your app,
            // but for a quick fix, using the default MaterialTheme works.
            MaterialTheme {
                Surface {
                    NavGraph()
                }
            }
        }
    }
}

// The @AndroidEntryPoint annotation is already provided by the Hilt library,
// so you don't need to define it yourself. You can safely remove this custom annotation.
// annotation class AndroidEntryPoint
