package com.example.calc

import android.content.res.Configuration
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Buttons(
    label:String, modifier: Modifier = Modifier, color: Color = Color.LightGray,
    onClick:(String) -> Unit
){
    Button(
        onClick = {onClick(label)},
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = modifier.padding(4.dp).height(100.dp)
    ) {
        Text(text = label, fontSize = 20.sp, color = Color.Black)
    }
}

@Composable
fun GridSimple(onAction: (String) -> Unit){
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalArrangement = Arrangement.Bottom) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Buttons("C/CE", Modifier.weight(1f), onClick = onAction)
            Buttons("AC", Modifier.weight(2f), onClick = onAction)
            Buttons("+\n-", Modifier.weight(1f), onClick = onAction)
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Buttons("7", Modifier.weight(1f), onClick = onAction)
            Buttons("8", Modifier.weight(1f), onClick = onAction)
            Buttons("9", Modifier.weight(1f), onClick = onAction)
            Buttons("/", Modifier.weight(1f), onClick = onAction)
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Buttons("4", Modifier.weight(1f), onClick = onAction)
            Buttons("5", Modifier.weight(1f), onClick = onAction)
            Buttons("6", Modifier.weight(1f), onClick = onAction)
            Buttons("*", Modifier.weight(1f), onClick = onAction)
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Buttons("1", Modifier.weight(1f), onClick = onAction)
            Buttons("2", Modifier.weight(1f), onClick = onAction)
            Buttons("3", Modifier.weight(1f), onClick = onAction)
            Buttons("-", Modifier.weight(1f), onClick = onAction)
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Buttons("0", Modifier.weight(1f), onClick = onAction)
            Buttons(".", Modifier.weight(1f), onClick = onAction)
            Buttons("=", Modifier.weight(1f), onClick = onAction)
            Buttons("+", Modifier.weight(1f), onClick = onAction)
        }
    }
}

@Composable
fun SimpleScreen() {
    // Estados para la lógica
    var displayText by remember { mutableStateOf("0") }
    var operand1 by remember { mutableStateOf<Double?>(null) }
    var operator by remember { mutableStateOf<String?>(null) }
    var shouldResetScreen by remember { mutableStateOf(false) }

    var lastClickTime by remember { mutableStateOf(0L) }
    val doubleClickDelay = 500L // milisegundos

    //val configuration = LocalConfiguration.current
    //val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE //horizontal

    Column {
        // Pantalla de visualización (puedes añadir un componente Text aquí)
        Text(
            text = displayText,
            fontSize = 48.sp,
            color = Color.White,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
                .horizontalScroll(rememberScrollState()),
            textAlign = TextAlign.End,
            maxLines = 1,
            softWrap = false
        )

        GridSimple { action ->
            when {
                // Si es un número o punto decimal
                action.all { it.isDigit() } || action == "." -> {
                    if (shouldResetScreen || displayText == "0") {
                        displayText = action
                        shouldResetScreen = false
                    } else {
                        displayText += action
                    }
                }
                // Limpiar pantalla
                action == "AC" -> {
                    displayText = "0"
                    operand1 = null
                    operator = null
                }
                // Operadores básicos
                action in listOf("+", "-", "*", "/") -> {
                    val currentNumber = displayText.toDoubleOrNull()

                    if (operand1 == null) {
                        operand1 = currentNumber
                    } else if (operator != null && !shouldResetScreen) {
                        // Solo calculamos si el usuario escribió un número nuevo
                        val secondOperand = currentNumber ?: 0.0
                        operand1 = when (operator) {
                            "+" -> operand1!! + secondOperand
                            "-" -> operand1!! - secondOperand
                            "*" -> operand1!! * secondOperand
                            "/" -> if (secondOperand != 0.0) operand1!! / secondOperand else 0.0
                            else -> operand1
                        }
                        displayText = operand1.toString()
                    }

                    // Esto siempre se ejecuta: actualiza al último operador pulsado
                    operator = action
                    shouldResetScreen = true
                }

                action == "C/CE" -> {
                    val currentTime = System.currentTimeMillis()

                    if (currentTime - lastClickTime < doubleClickDelay) {
                        //Doble clic, reseteo total
                        displayText = "0"
                        operand1 = null
                        operator = null
                        shouldResetScreen = false
                    } else {
                        //Borra el 2o operando, pero mantiene la operacion
                        displayText = "0"

                    /*if (displayText.isNotEmpty() && displayText != "0") {
                        // Elimina el último carácter
                        displayText = displayText.dropLast(1)

                        // Si queda vacío o solo un signo negativo, vuelve a "0"
                        if (displayText.isEmpty() || displayText == "-") {
                            displayText = "0"
                        } */
                    }
                    lastClickTime = currentTime
                }

                action == "+\n-" ->{
                    if (displayText != "0") { // No cambiamos el signo si es cero
                        displayText = if (displayText.startsWith("-")) {
                            displayText.substring(1) // Si es negativo, quito el '-'
                        } else {
                            "-$displayText" // Si es positivo, añado el '-' al inicio
                        }
                    }
                }

                // Calcular resultado
                action == "=" -> {
                    val secondOperand = displayText.toDoubleOrNull()
                    if (operand1 != null && operator != null && secondOperand != null) {
                        val result = when (operator) {
                            "+" -> operand1!! + secondOperand
                            "-" -> operand1!! - secondOperand
                            "*" -> operand1!! * secondOperand
                            "/" -> if (secondOperand != 0.0) operand1!! / secondOperand else "Error"
                            else -> 0.0
                        }
                        displayText = result.toString()
                        operand1 = null
                        operator = null
                        shouldResetScreen = true
                    }
                }
            }
        }
    }
}

