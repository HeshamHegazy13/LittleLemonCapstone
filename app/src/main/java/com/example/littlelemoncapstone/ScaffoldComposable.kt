package com.example.littlelemoncapstone



import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldComposable(
    sharedPrefs :SharedPreferences ,
    onNavigateToDesiredScreen :()->Unit ,
    onNavigateToProfileScreen :()->Unit ,
    databaseMenuItems: List<MenuItemRoom>){

    val isAuthenticated = sharedPrefs.getBoolean("auth", false)

    Scaffold(
        modifier = Modifier.fillMaxHeight(),
        topBar = { TopAppBar(sharedPrefs = sharedPrefs,onNavigateToProfileScreen=onNavigateToProfileScreen ) },
    ) {


        if (isAuthenticated) {
            HomeScreen(sharedPrefs = sharedPrefs ,onNavigateToBoardingScreen = onNavigateToDesiredScreen , databaseMenuItems = databaseMenuItems )
        } else {
             OnboardingScreen(sharedPrefs = sharedPrefs ,onNavigateToWelcomeScreen = onNavigateToDesiredScreen)
        }
    }
}


