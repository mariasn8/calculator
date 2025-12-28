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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            Buttons("CE", Modifier.weight(1f), onClick = onAction)
            Buttons("C", Modifier.weight(2f), onClick = onAction)
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
fun SimpleScreen(){
    GridSimple {  }
}

