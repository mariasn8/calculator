package com.example.calc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.toString

@Composable
fun GridAdvanced(onAction: (String) -> Unit){
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalArrangement = Arrangement.Bottom) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Buttons("CE", Modifier.weight(1f), onClick = onAction)
            Buttons("C", Modifier.weight(2f), onClick = onAction)
            Buttons("+\n-", Modifier.weight(1f), onClick = onAction)
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Buttons("sin", Modifier.weight(1f), onClick = onAction)
            Buttons("cos", Modifier.weight(1f), onClick = onAction)
            Buttons("tan", Modifier.weight(1f), onClick = onAction)
            Buttons("ln", Modifier.weight(1f), onClick = onAction)
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Buttons("sqrt", Modifier.weight(1f), onClick = onAction)
            Buttons("x^2", Modifier.weight(1f), onClick = onAction)
            Buttons("x^y", Modifier.weight(1f), onClick = onAction)
            Buttons("log", Modifier.weight(1f), onClick = onAction)
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

@Composable  //ESTO ES UNA COPIA DE LA BASICA
fun AdvancedScreen(){
    // Estados para la lógica
    var displayText by remember { mutableStateOf("0") }
    var operand1 by remember { mutableStateOf<Double?>(null) }
    var operator by remember { mutableStateOf<String?>(null) }
    var shouldResetScreen by remember { mutableStateOf(false) }

    Column {
        // Pantalla de visualización (puedes añadir un componente Text aquí)
        Text(
            text = displayText,
            fontSize = 48.sp,
            color = Color.White,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            textAlign = TextAlign.End
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
                action == "C" -> {
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

                action == "CE" -> {
                    if (displayText.isNotEmpty() && displayText != "0") {
                        // Elimina el último carácter
                        displayText = displayText.dropLast(1)

                        // Si queda vacío o solo un signo negativo, vuelve a "0"
                        if (displayText.isEmpty() || displayText == "-") {
                            displayText = "0"
                        }
                    }
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