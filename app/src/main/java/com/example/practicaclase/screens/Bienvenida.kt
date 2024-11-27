package com.example.practicaclase.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun BienvenidaScreen(modifier: Modifier, navController: NavController, datos:String?) {
    val nombre = datos?.split(";")?.get(0)
    val dni = datos?.split(";")?.get(1)
    val edad = datos?.split(";")?.get(2)
    Bienvenida(modifier, navController, nombre, dni, edad)
}

@Composable
fun Bienvenida(modifier: Modifier, navController: NavController, nombre:String?, dni:String?, edad:String?) {
    Column(modifier = modifier.fillMaxSize().padding(top = 16.dp, bottom = 16.dp)) {

        Row(Modifier.fillMaxWidth().background(Color(0xFF71AAE0)).padding(bottom = 16.dp, top = 16.dp)) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                modifier = Modifier.clickable { navController.popBackStack() }.padding(end = 16.dp, start = 16.dp)
            )
            Text("Bienvenida")
        }

        Spacer(Modifier.height(100.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(end = 30.dp, start = 30.dp)) {
            Text("Bienvenido $nombre", fontSize = 28.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(10.dp))
            Text("Se ha registrado correctamente su edad: $edad y su dni: $dni", textAlign = TextAlign.Center)
        }

    }
}