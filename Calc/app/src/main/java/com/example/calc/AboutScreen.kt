package com.example.calc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AboutScreen(){
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "App name -> Calculator\n Author -> María Sánchez Nieto\n" +
                    "Functionalities -> There are 2 calculator options:\n\t\t" +
                    "- A simple calculator: basic operations (+, -, *, /)\n\t\t" +
                    "- A scientific calculator: allows more complex operations like trigonometric" +
                    " functions or exponentiation.\n\n" +
                    "If the result is a long number it can be scrolled to the right to see it" +
                    " completely.", color = Color.White,
            fontSize = 18.sp
        )
    }
}