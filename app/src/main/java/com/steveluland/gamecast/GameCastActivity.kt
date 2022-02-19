package com.steveluland.gamecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.steveluland.gamecast.characterlist.ui.CharacterListScreen
import com.steveluland.gamecast.ui.theme.GameCastTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameCastActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameCastApp()
        }
    }
}

@Composable
fun GameCastApp() {
    GameCastTheme {
        val navController = rememberNavController()

        GameCastNavHost(navController = navController)
    }
}

@Composable
fun GameCastNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "characterList") {
        composable("characterList") { CharacterListScreen() }
    }
}