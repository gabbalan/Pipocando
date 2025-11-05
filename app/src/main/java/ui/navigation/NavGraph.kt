package com.example.pipocando_oficial.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pipocando_oficial.ui.screens.*

object Routes {
    const val Login = "login"
    const val Home = "home"
    const val Search = "search"
    const val Detail = "detail/{imdbId}"
    const val Profile = "profile"
}

@Composable
fun AppNavGraph(nav: NavHostController) {
    NavHost(navController = nav, startDestination = Routes.Login) {
        composable(Routes.Login) {
            LoginScreen(onLogged = {
                nav.navigate(Routes.Home) {
                    popUpTo(Routes.Login) { inclusive = true }
                }
            })
        }
        composable(Routes.Home) {
            HomeScreen(
                onGoSearch = { nav.navigate(Routes.Search) },
                onGoProfile = { nav.navigate(Routes.Profile) }
            )
        }
        composable(Routes.Search) {
            SearchScreen(onOpenDetail = { id -> nav.navigate("detail/$id") })
        }
        composable("detail/{imdbId}") { backStackEntry ->
            val imdbId = backStackEntry.arguments?.getString("imdbId") ?: ""
            DetailScreen(imdbId = imdbId)
        }
        composable(Routes.Profile) { ProfileScreen() }
    }
}
