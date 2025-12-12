package com.example.calc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calc.ui.theme.CalcTheme
import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalcTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black,

                content = {
                    navigation()
                }
                )
            }

        }
    }
}



@Composable
fun mainScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
    ) {

        Text(
            text = "Calculator",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {navController.navigate("simpleScreen") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            modifier = Modifier.width(300.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("Simple")
        }
        Button(onClick = {navController.navigate("advancedScreen") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            modifier = Modifier.width(300.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("Advanced")
        }
        Button(onClick = {navController.navigate("aboutScreen") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            modifier = Modifier.width(300.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("About")
        }
        Button(onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            modifier = Modifier.width(300.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("Exit")
        }
    }
}

@Composable
fun navigation(){
    val nav = rememberNavController()

    NavHost(
        navController = nav,
        startDestination = "mainScreen"
    ){
        composable("mainScreen") {
            mainScreen(navController = nav)
        }

        composable("simpleScreen") {
            simpleScreen()
        }

        composable ("advancedScreen"){
            advancedScreen()
        }

        composable("aboutScreen") {
            aboutScreen()
        }
    }
}

@Composable
fun simpleScreen(){ //PONER BOTON PARA VOLVER A MAINSCREEN EN LAS 3

}

@Composable
fun advancedScreen(){

}

@Composable
fun aboutScreen(){

}

