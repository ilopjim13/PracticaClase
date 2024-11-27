package com.example.practicaclase.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.practicaclase.screens.BienvenidaScreen
import com.example.practicaclase.screens.RegistroScreen

@Composable
fun AppNavigation(modifier: Modifier) {
    // para esta practica utilizo el controlador para viajar entre pantallas ya que es lo que vimos en clase
    val navControlador = rememberNavController()
    NavHost(navController = navControlador, startDestination = AppScreen.RegistroScreen.route) {
        composable(AppScreen.RegistroScreen.route) {
            RegistroScreen(modifier, navControlador)
        }
        composable(AppScreen.BienvenidaScreen.route + "/{text}", arguments = listOf(navArgument(name = "text") {
            type = NavType.StringType
        })) {
            BienvenidaScreen(modifier, navControlador, it.arguments?.getString("text"))
        }
    }
}