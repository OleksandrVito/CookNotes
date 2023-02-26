package ua.vitolex.cooknotes.feature_note.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ua.vitolex.cooknotes.feature_note.presentation.screens.add_edit.AddEditScreen
import ua.vitolex.cooknotes.feature_note.presentation.screens.details.DetailsScreen
import ua.vitolex.cooknotes.feature_note.presentation.screens.main.MainScreen

sealed class Screens(val rout: String) {
    object MainScreen : Screens(rout = "main_screen")
    object DetailsScreen : Screens(rout = "details_screen")
    object AddEditScreen : Screens(rout = "add_edit_screen")
}

@Composable
fun SetupNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.MainScreen.rout) {
        composable(route = Screens.MainScreen.rout) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screens.DetailsScreen.rout + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            DetailsScreen(navController = navController, it.arguments?.getString("id"))
        }
        composable(route = Screens.AddEditScreen.rout) {
            AddEditScreen(navController = navController)
        }
    }
}