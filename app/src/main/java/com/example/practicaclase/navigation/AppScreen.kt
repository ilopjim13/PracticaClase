package com.example.practicaclase.navigation

sealed class AppScreen(val route:String) {
    object RegistroScreen:AppScreen("Registro")
    object BienvenidaScreen:AppScreen("Bienvenida")
}