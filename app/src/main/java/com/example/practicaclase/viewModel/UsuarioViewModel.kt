package com.example.practicaclase.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class UsuarioViewModel {

    private val _dni = mutableStateOf("")
    var dni:State<String> = _dni

    private val _nombre = mutableStateOf("")
    var nombre:State<String> = _nombre

    private val _edad = mutableStateOf("")
    var edad:State<String> = _edad

    private val _errorMenssage = mutableStateOf("")
    var errorMenssage:State<String> = _errorMenssage

    private val _error = mutableStateOf(false)
    var error:State<Boolean> = _error

    private val _button = mutableStateOf(false)
    var button:State<Boolean> = _button


    // cada vez que escribamos se van a cambiar los valores a los que le correspondan, se puede usar para todos ya que se le pasas el it al estes esribiendo
    // lo utilizo así ya que en desarrollo de interfaces lo hemos visto y es una buena solucion
    fun onChangeValue(dni:String, nombre:String, edad:String) {
        _dni.value = dni
        _nombre.value = nombre
        _edad.value = edad
        _errorMenssage.value = checkError(dni, edad)
        _error.value = _errorMenssage.value.isNotBlank()
        _button.value = false
    }

    // al clickar pone el boton en true y si los parametros estan vacios salta el error en cambio si están llenos comprueba de nuevo si están bien todos los campos
    fun onClickButton() {
        _button.value = true
        if(_nombre.value.isBlank() || _dni.value.isBlank() || _edad.value.isBlank()){
            _error.value = true
            _errorMenssage.value = "Debes rellenar todos los campos"
        }
        else if (checkError(dni.value, _edad.value).isBlank()) {
            _error.value = false
        }
    }

    // retorna el mensaje de error si es que lo hay, si es así pone el error a true y al darle al boton saldrá el error
    private fun checkError(dni: String, edad: String):String {
        if(dni.isNotBlank()){
            // esto obtiene los 8 primeros caracteres y lo intento convertir, si falla dniNum valdrá null
            val dniNum = dni.take(8).toIntOrNull()
            // esto obtiene el ultimo caracter y lo pone en mayusculas y comprueba si es una letra si está entre la A y la Z
            val dniLetra = dni.last().uppercase() in ("A".."Z")

            if (dni.length != 9) return "El numero de caracteres del dni están mal"
            else if (dniNum == null) return "Los 8 primero caracteres deben ser numeros"
            else if (!dniLetra) return "El último dígito del dni debe ser una letra"
        }
        if (edad.isNotBlank()) {
            if (edad.toIntOrNull() == null) return "La edad debe ser un número"
        }
        return ""

    }

}