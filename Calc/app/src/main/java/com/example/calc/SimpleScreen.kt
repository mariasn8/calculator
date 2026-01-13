package com.example.calc

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Buttons(
    label:String, modifier: Modifier = Modifier, color: Color = Color.LightGray,
    onClick:(String) -> Unit
){
    Button(
        onClick = {onClick(label)},
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = modifier.padding(4.dp)
    ) {
        Text(text = label, fontSize = 30.sp, color = Color.Black)
    }
}

@Composable
fun GridSimple(onAction: (String) -> Unit){
    Column(modifier = Modifier.fillMaxWidth().padding(12.dp),
        verticalArrangement = Arrangement.Bottom) {
        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Buttons("C/CE", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("AC", Modifier.weight(2f).fillMaxHeight(), onClick = onAction)
            Buttons("+\n-", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
        }

        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Buttons("7", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("8", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("9", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("/", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
        }

        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Buttons("4", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("5", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("6", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("*", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
        }

        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Buttons("1", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("2", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("3", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("-", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
        }

        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Buttons("0", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons(".", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("=", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("+", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
        }
    }
}

@Composable
fun GridSimpleLandscape(onAction: (String) -> Unit){
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalArrangement = Arrangement.Bottom) {
        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Buttons("C/CE", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("AC", Modifier.weight(2f).fillMaxHeight(), onClick = onAction)
            Buttons("+\n-", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("/", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
        }

        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Buttons("6", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("7", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("8", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("9", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("*", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
        }

        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Buttons("2", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("3", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("4", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("5", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("-", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
        }

        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Buttons("0", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("1", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons(".", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("=", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("+", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
        }


    }
}

@Composable
fun SimpleScreen() {
    // Estados para la lógica
    var displayText by rememberSaveable { mutableStateOf("0") }
    var operand1 by rememberSaveable { mutableStateOf<Double?>(null) }
    var operator by rememberSaveable { mutableStateOf<String?>(null) }
    var shouldResetScreen by rememberSaveable { mutableStateOf(false) }

    var lastClickTime by remember { mutableStateOf(0L) }
    val doubleClickDelay = 500L // milisegundos

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE //horizontal

    val context = LocalContext.current

    Column {
        // Pantalla de visualización
        Text(
            text = displayText,
            fontSize = 60.sp,
            color = Color.White,
            modifier = Modifier.fillMaxWidth().padding(50.dp)
                .horizontalScroll(rememberScrollState()),
            textAlign = TextAlign.End,
            maxLines = 1,
            softWrap = false,
        )

        if (isLandscape) {
            GridSimpleLandscape { action ->
                when{
                    action.all { it.isDigit() } || action == "." -> {
                        if (action == ".") {
                            // Logica punto
                            if (shouldResetScreen) {
                                // Si venimos de un resultado anterior, empezamos con "0."
                                displayText = "0."
                                shouldResetScreen = false
                            } else if (!displayText.contains(".")) {
                                // Solo añadimos el punto si la pantalla no contiene uno ya
                                displayText += "."
                            } else {
                                Toast.makeText(context,
                                    "Error",
                                    Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            // Logica numeros
                            if (shouldResetScreen || displayText == "0") {
                                displayText = action
                                shouldResetScreen = false
                            } else {
                                displayText += action
                            }
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
                            //if (displayText.isNotEmpty() && displayText != "0") {
                            // Elimina el último carácter
                            displayText = displayText.dropLast(1)

                            // Si queda vacío o solo un signo negativo, es "0"
                            if (displayText.isEmpty() || displayText == "-") {
                                displayText = "0"
                            }
                        }
                        lastClickTime = currentTime
                    }

                    action == "+\n-" -> {
                        if (displayText != "0") { // No cambiamos el signo si es cero
                            displayText = if (displayText.startsWith("-")) {
                                displayText.substring(1) // Si es negativo, quito '-'
                            } else {
                                "-$displayText" // Si es positivo, añado '-' al inicio
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
                                "/" -> if (secondOperand != 0.0) operand1!! / secondOperand
                                    else
                                        Toast.makeText(context,
                                        "Error",
                                        Toast.LENGTH_SHORT).show()
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

        } else {
            GridSimple { action ->
                when {
                    // Si es un número o punto decimal
                    action.all { it.isDigit() } || action == "." -> {
                        if (action == ".") {
                            // Logica punto
                            if (shouldResetScreen) {
                                // Si venimos de un resultado anterior, empezamos con "0."
                                displayText = "0."
                                shouldResetScreen = false
                            } else if (!displayText.contains(".")) {
                                // Solo añadimos el punto si la pantalla no contiene uno ya
                                // Esto evita el error de tener "1.2.3"
                                displayText += "."
                            }
                        } else {
                            // Logica numeros
                            if (shouldResetScreen || displayText == "0") {
                                displayText = action
                                shouldResetScreen = false
                            } else {
                                displayText += action
                            }
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
                            //if (displayText.isNotEmpty() && displayText != "0") {
                            // Elimina el último carácter
                            displayText = displayText.dropLast(1)

                            // Si queda vacío o solo un signo negativo, es "0"
                            if (displayText.isEmpty() || displayText == "-") {
                                displayText = "0"
                            }
                        }
                        lastClickTime = currentTime
                    }

                    action == "+\n-" -> {
                        if (displayText != "0") { // No cambiamos el signo si es cero
                            displayText = if (displayText.startsWith("-")) {
                                displayText.substring(1) // Si es negativo, quito '-'
                            } else {
                                "-$displayText" // Si es positivo, añado '-' al inicio
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
                                "/" -> if (secondOperand != 0.0) operand1!! / secondOperand
                                    else
                                        Toast.makeText(context,
                                        "Error",
                                        Toast.LENGTH_SHORT).show()
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
}


