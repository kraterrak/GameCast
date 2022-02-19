package com.steveluland.gamecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.steveluland.gamecast.characterdetail.CharacterDetailScreen
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
        composable(
            route = CHARACTER_LIST_ROUTE
        ) {
            CharacterListScreen { guid ->
                navController.navigate("$CHARACTER_DESCRIPTION_ROUTE?guid=$guid")
            }
        }
        composable(
            route = "$CHARACTER_DESCRIPTION_ROUTE?guid={guid}",
            arguments = listOf(
                navArgument("guid") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            CharacterDetailScreen(guid = backStackEntry.arguments?.getString("guid"))
        }
    }
}

const val CHARACTER_LIST_ROUTE = "characterList"
const val CHARACTER_DESCRIPTION_ROUTE = "characterDescription"