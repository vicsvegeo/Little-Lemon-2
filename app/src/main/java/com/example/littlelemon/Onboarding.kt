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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun Onboarding(navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var emailAddress by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var success by remember { mutableStateOf(false) }
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
            text = "Let's get to know you!",
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
        Text(
            text = "Personal Information:",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.markazi)),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First name") },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.White
                ),
                modifier = Modifier
                    .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                    .fillMaxWidth(0.95f)
            )
            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last name") },
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.White
                ),
                modifier = Modifier
                    .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                    .fillMaxWidth(0.95f)
            )
            TextField(
                value = emailAddress,
                onValueChange = { emailAddress = it },
                label = { Text("Email") },
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.White
                ),
                modifier = Modifier
                    .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                    .fillMaxWidth(0.95f)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                    if (firstName.isBlank() || lastName.isBlank() || emailAddress.isBlank()) {
                        showDialog = true
                    }
                    else{
                        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                        with(sharedPreferences.edit()) {
                            putString("firstName", firstName)
                            putString("lastName", lastName)
                            putString("email", emailAddress)
                            putBoolean("loggedIn", true)
                            apply()
                        }
                        success = true

                    }
                }, modifier = Modifier.fillMaxWidth(.95f).height(50.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(
                        id = R.color.primary_yellow
                    ), contentColor = Color.Black
                ), border = BorderStroke(2.dp, colorResource(id = R.color.secondary_peach))
            ) {
                Text(
                    text = "Register", style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.karla)), fontSize = 20.sp
                    )
                )
            }
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(text = "Information Not Found") },
                    text = { Text("Please fill in all of the fields") },
                    confirmButton = {
                        Button(onClick = { showDialog = false }) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showDialog = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }
            if (success){
                AlertDialog(
                    onDismissRequest = { success = false; navController.navigate(ProfileScreen.route) },
                    title = { Text(text = "Registration Complete!") },
                    text = { Text("You've been successfully registered.") },
                    confirmButton = {
                        Button(onClick = { navController.navigate(ProfileScreen.route);success = false }) {
                            Text("OK")
                        }
                    }
                )
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Onboarding(navController = rememberNavController())
}