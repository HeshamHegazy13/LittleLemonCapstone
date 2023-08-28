package com.example.littlelemoncapstone

interface Destinations {
    val route:String
}
object OnboardingScreen : Destinations {override val route = "OnboardingScreen"}
object WelcomeScreen : Destinations {override val route = "WelcomeScreen"}
object ScaffoldScreen : Destinations {override val route = "ScaffoldScreen"}
object ProfileScreen : Destinations {override val route = "ProfileScreen"}

