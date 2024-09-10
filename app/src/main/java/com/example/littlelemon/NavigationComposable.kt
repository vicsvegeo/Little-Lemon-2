package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController, menuItems: List<MenuItemRoom>){
    val context = LocalContext.current
    val startDestination = if (isUserLoggedIn(context)) HomeScreen.route else OnboardingScreen.route

    NavHost(navController = navController, startDestination = startDestination){
        composable(Destinations.ONBOARDING){ Onboarding(navController)}
        composable(Destinations.HOME){ Home(menuItems, navController)}
        composable(Destinations.PROFILE){ Profile(navController) }

    }


}

fun isUserLoggedIn(context: Context): Boolean {
    val sharedPrefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    return sharedPrefs.getBoolean("loggedIn", false)
}