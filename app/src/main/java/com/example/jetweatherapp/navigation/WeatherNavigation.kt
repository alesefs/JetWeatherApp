package com.example.jetweatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetweatherapp.screens.about.WeatherAboutScreen
import com.example.jetweatherapp.screens.favorite.WeatherFavoriteScreen
import com.example.jetweatherapp.screens.main.WeatherMainScreen
import com.example.jetweatherapp.screens.main.WeatherMainViewModel
import com.example.jetweatherapp.screens.search.WeatherSearchScreen
import com.example.jetweatherapp.screens.settings.WeatherSettingsScreen
import com.example.jetweatherapp.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreens.name
    ) {
        composable(WeatherScreens.SplashScreens.name) {
            WeatherSplashScreen(navController = navController)
        }

        val route = WeatherScreens.MainScreen.name
        composable(
            route = "$route/{city}",
            arguments = listOf(
                navArgument(
                    name = "city"
                ) {
                    type = NavType.StringType
                }
            )
        ) { navBack ->

            navBack.arguments?.getString("city").let { city ->

                val mainViewModel = hiltViewModel<WeatherMainViewModel>()
                WeatherMainScreen(navController = navController, mainViewModel, city = city)
            }
        }

        composable(WeatherScreens.SearchScreen.name) {
            WeatherSearchScreen(navController = navController)
        }

        composable(WeatherScreens.AboutScreen.name) {
            WeatherAboutScreen(navController = navController)
        }

        composable(WeatherScreens.FavoriteScreen.name) {
            WeatherFavoriteScreen(navController = navController)
        }

        composable(WeatherScreens.SettingsScreen.name) {
            WeatherSettingsScreen(navController = navController)
        }
    }
}