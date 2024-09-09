package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Profile(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString("firstName", "error") ?: "error: missing"
    val lastName = sharedPreferences.getString("lastName", "error") ?: "error: missing"
    val email = sharedPreferences.getString("email", "error") ?: "error: missing"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, bottom = 24.dp),
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "little lemon logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Personal Information",
            modifier = Modifier
                .background(
                    color = colorResource(
                        id = R.color.primary_green
                    )
                )
                .fillMaxWidth()
                .height(100.dp)
                .padding(vertical = 25.dp)
                .align(alignment = Alignment.CenterHorizontally),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.markazi)),
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.white),
            )
        )
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "First Name", modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.karla)), fontSize = 20.sp
                    )
                )
                Text(
                    text = firstName,
                    modifier = Modifier
                        .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                        .fillMaxWidth(0.95f)
                        .padding(12.dp),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.karla)), fontSize = 20.sp
                    )
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Last Name", modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.karla)), fontSize = 20.sp
                    )
                )
                Text(
                    text = lastName,
                    modifier = Modifier
                        .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                        .fillMaxWidth(0.95f)
                        .padding(12.dp),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.karla)), fontSize = 20.sp
                    )
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Email Address", modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.karla)), fontSize = 20.sp
                    )
                )
                Text(
                    text = email,
                    modifier = Modifier
                        .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                        .fillMaxWidth(0.95f)
                        .padding(12.dp),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.karla)), fontSize = 20.sp
                    )
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                    sharedPreferences.edit().clear().apply(); navController.navigate(
                    OnboardingScreen.route
                )
                }, modifier = Modifier.fillMaxWidth(0.95f).height(60.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(
                        id = R.color.primary_yellow
                    ), contentColor = Color.Black
                ), border = BorderStroke(2.dp, colorResource(id = R.color.secondary_peach))
            ) {
                Text(
                    text = "Log Out", style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.karla)), fontSize = 20.sp
                    )
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(navController = rememberNavController())
}