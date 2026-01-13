package com.example.calc

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calc.ui.theme.CalcTheme


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
                    Navigation()
                }
                )
            }

        }
    }
}



@Composable
fun MainScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
    ) {

        Text(
            text = "Calculator",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 40.sp
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
            Text("Simple", fontSize = 25.sp)
        }
        Button(onClick = {navController.navigate("advancedScreen") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            modifier = Modifier.width(300.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("Advanced", fontSize = 25.sp)
        }
        Button(onClick = {navController.navigate("aboutScreen") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            modifier = Modifier.width(300.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("About", fontSize = 25.sp)
        }

        val context= LocalContext.current
        Button(onClick = {(context as? Activity)?.finish() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            modifier = Modifier.width(300.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("Exit", fontSize = 25.sp)
        }
    }
}

@Composable
fun Navigation(){
    val nav = rememberNavController()

    NavHost(
        navController = nav,
        startDestination = "mainScreen"
    ){
        composable("mainScreen") {
            MainScreen(navController = nav)
        }

        composable("simpleScreen") {
            SimpleScreen()
        }

        composable ("advancedScreen"){
            AdvancedScreen()
        }

        composable("aboutScreen") {
            AboutScreen()
        }
    }
}



