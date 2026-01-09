package com.example.calc

import android.content.res.Configuration
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
import androidx.compose.remote.creation.compose.state.pow
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
import kotlin.math.pow
import kotlin.math.*
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext



@Composable
fun GridAdvanced(onAction: (String) -> Unit){
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalArrangement = Arrangement.Bottom) {
        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Buttons("C/CE", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("AC", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("%", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("+\n-", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
        }

        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Buttons("sin", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("cos", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("tan", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("ln", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
        }

        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Buttons("sqrt", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("x^2", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("x^y", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("log", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
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

@Composable     //CAMBIARLO
fun GridAdvancedLanscape(onAction: (String) -> Unit){
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalArrangement = Arrangement.Bottom) {
        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Buttons("sin", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("cos", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("C/CE", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("AC", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons(".", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("*", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("/", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
        }

        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Buttons("ln", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("tan", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("+\n-", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("7", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("8", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("9", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("-", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
        }

        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Buttons("log", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("sqrt", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("%", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("4", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("5", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("6", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("+", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
        }

        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            Buttons("x^2", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("x^y", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("0", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("1", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("2", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("3", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
            Buttons("=", Modifier.weight(1f).fillMaxHeight(), onClick = onAction)
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

    var lastClickTime by remember { mutableStateOf(0L) }
    val doubleClickDelay = 500L // milisegundos

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE //horizontal
    val context = LocalContext.current

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

        if(isLandscape){
            GridAdvancedLanscape { action ->

            }
        }
        else{
            GridAdvanced { action ->
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
                    action in listOf("+", "-", "*", "/", "x^y") -> {
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
                                "x^y" -> operand1!!.pow(secondOperand)
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

                            // Si queda vacío o solo un signo negativo, vuelve a "0"
                            if (displayText.isEmpty() || displayText == "-") {
                                displayText = "0"
                            }
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

                    action == "sqrt" -> {
                        val currentValue = displayText.toDoubleOrNull()
                        if (currentValue != null) {
                            if (currentValue >= 0) {
                                // Calculamos la raíz y actualizamos el texto
                                val result = kotlin.math.sqrt(currentValue)
                                displayText = result.toString()
                                // Marcamos que la pantalla debe resetearse al escribir el siguiente número
                                shouldResetScreen = true
                            } else {
                                Toast.makeText(context,
                                    "Error",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    action == "x^2" -> {
                        val currentValue = displayText.toDoubleOrNull()
                        if (currentValue != null) {
                            val result = currentValue * currentValue

                            // Mostramos el resultado.
                            // Usamos un formateo simple para evitar demasiados decimales si es necesario
                            displayText = result.toString()

                            // Marcamos que la pantalla debe limpiarse al escribir el siguiente número
                            shouldResetScreen = true
                        }
                    }

                    action == "log" -> {
                        val currentValue = displayText.toDoubleOrNull()
                        if (currentValue != null && currentValue > 0) {
                            // Logaritmo en base 10
                            displayText = kotlin.math.log10(currentValue).toString()
                            shouldResetScreen = true
                        } else {
                            Toast.makeText(context,
                                "Error",
                                Toast.LENGTH_SHORT).show()
                        // El logaritmo no existe para números <= 0
                        }
                    }

                    action == "ln" -> {
                        val currentValue = displayText.toDoubleOrNull()
                        if (currentValue != null && currentValue > 0) {
                            // Logaritmo natural (base e)
                            displayText = kotlin.math.ln(currentValue).toString()
                            shouldResetScreen = true
                        } else {
                            Toast.makeText(context,
                                "Error",
                                Toast.LENGTH_SHORT).show()
                        }
                    }

                    action == "sin" -> {
                        val currentValue = displayText.toDoubleOrNull()
                        if (currentValue != null) {
                            //sin(90)=1.0
                            val result = kotlin.math.sin(Math.toRadians(currentValue))

                            displayText = if (kotlin.math.abs(result) < 1e-10) {
                                "0.0"
                            } else {
                                "%.10f".format(result).trimEnd('0').trimEnd('.')
                            }
                            shouldResetScreen = true
                        }
                    }

                    action == "cos" -> {
                        val currentValue = displayText.toDoubleOrNull()
                        if (currentValue != null) {
                            //cos(90)=0.0
                            val result = kotlin.math.cos(Math.toRadians(currentValue))

                            displayText = if (kotlin.math.abs(result) < 1e-10) {
                                "0.0"
                            } else {
                                "%.10f".format(result).trimEnd('0').trimEnd('.')
                            }
                            shouldResetScreen = true
                        }
                    }

                    action == "tan" -> {
                        val currentValue = displayText.toDoubleOrNull()
                        if (currentValue != null) {
                            val result = kotlin.math.tan(Math.toRadians(currentValue))

                            displayText = if (kotlin.math.abs(result) < 1e-10) {
                                "0.0"
                            } else {
                                "%.10f".format(result).trimEnd('0').trimEnd('.')
                            }
                            shouldResetScreen = true
                        }
                    }

                    action == "%" -> {
                        val currentNum = displayText.toDoubleOrNull() ?: 0.0
                        if (operand1 != null && operator != null) {
                            // Porcentaje del primer numero
                            val percentageValue = (operand1!! * currentNum) / 100

                            displayText = when (operator) {
                                "+", "-" -> percentageValue.toString() // Muestra cuanto sumar/restar
                                "*", "/" -> (currentNum / 100).toString() // Conversion decimal estandar
                                else -> (currentNum / 100).toString()
                            }
                        } else {
                            // Sin operacion: solo divide entre 100
                            displayText = (currentNum / 100).toString()
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
                                "x^y" -> operand1!!.pow(secondOperand)
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