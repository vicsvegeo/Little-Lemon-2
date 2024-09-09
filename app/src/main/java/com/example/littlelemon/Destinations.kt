package com.example.littlelemon

interface Destinations {
    companion object {
        const val ONBOARDING = "onboarding"
        const val HOME = "home"
        const val PROFILE = "profile"
    }
}

object OnboardingScreen : Destinations {
    const val route = Destinations.ONBOARDING
}

object HomeScreen : Destinations {
    const val route = Destinations.HOME
}

object ProfileScreen : Destinations {
    const val route = Destinations.PROFILE
}