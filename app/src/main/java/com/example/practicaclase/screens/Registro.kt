package com.example.practicaclase.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practicaclase.navigation.AppScreen
import com.example.practicaclase.viewModel.UsuarioViewModel

@Composable
fun RegistroScreen(modifier: Modifier, navController: NavController) {
    val usuarioViewModel = UsuarioViewModel()
    Registro(modifier, usuarioViewModel, navController)
}


@Composable
fun Registro(
    modifier: Modifier = Modifier,
    usuarioViewModel: UsuarioViewModel,
    navController: NavController
) {

    // creo las variables a utilizar desde el viewModel creado para manejar mejor los estados
    // y poder usar funciones para comprobar los datos
    val nombre by usuarioViewModel.nombre
    val edad by usuarioViewModel.edad
    val dni by usuarioViewModel.dni
    val error by usuarioViewModel.error
    val button by usuarioViewModel.button
    val errorMenssage by usuarioViewModel.errorMenssage

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.fillMaxSize()) {

        Cabecera()

        EntradaTexto(nombre, dni, edad, usuarioViewModel)

        // si no hay ningun error y los datos no están en blanco utilizo el navigate
        // para ir a la otra screen, y le paso todos los datos separados por ;
        // utilizo el navigate ya que lo hemos visto en clase y es lo que utilicé para el whatsapp también
        Button(onClick = {
            usuarioViewModel.onClickButton()
            if (nombre.isNotBlank() && dni.isNotBlank() && edad.isNotBlank() && !error) navController.navigate(
                route = AppScreen.BienvenidaScreen.route + "/" + nombre + ";" + dni + ";" + edad
            )
        }) {
            Text("Registrar")
        }

        Spacer(Modifier.height(20.dp))

        // Si hay algún error y se ha pulsado el botón saltará un mensaje de error
        Column(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color(0xFFFF9E9E))) {
            if (error && button) {
                Text(errorMenssage, Modifier.padding(15.dp))
            }
        }


    }
}


@Composable
fun EntradaTexto(nombre:String, dni:String,edad:String, usuarioViewModel:UsuarioViewModel) {
    // para las entradas de texto utilizo la funcion del viewModel para cambiar
    // el valor que corresponda y ver por pantalla lo que escribimos
    OutlinedTextField(
        value = nombre,
        onValueChange = { usuarioViewModel.onChangeValue(dni, it, edad) },
        placeholder = { Text("Nombre") })
    Spacer(Modifier.height(10.dp))

    OutlinedTextField(
        value = dni,
        onValueChange = { usuarioViewModel.onChangeValue(it, nombre, edad) },
        placeholder = { Text("DNI") })
    Spacer(Modifier.height(10.dp))

    OutlinedTextField(
        value = edad,
        onValueChange = { usuarioViewModel.onChangeValue(dni, nombre, it) },
        placeholder = { Text("Edad") })
    Spacer(Modifier.height(15.dp))
}

@Composable
fun Cabecera() {
    // Creo la cabecera de la aplicación
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .background(Color(0xFF71AAE0))
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
    ) {

        Text("Prueba prática")

    }

    Spacer(Modifier.height(60.dp))
}
