package com.example.littlelemoncapstone

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemoncapstone.Composables.theme.LittleLemonCapstoneTheme
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    private val sharedPrefs by lazy { getSharedPreferences("littleLemon",MODE_PRIVATE)}
    private val client = HttpClient(Android){
        install(ContentNegotiation){
            json(contentType = ContentType("text", "plain"))
        }
    }
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database")
            .build()
    }

    private suspend fun getMenu(): List<MenuItemNetwork> {
        return client
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body<MenuNetwork>().menu
    }
    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }


    private val menuItemsLiveData = MutableLiveData<String>()


    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())

            LittleLemonCapstoneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    lifecycleScope.launch(Dispatchers.IO) {
                        if (database.menuItemDao().isEmpty()) {
                            val menuItemsNetwork = getMenu()
                            saveMenuToDatabase(menuItemsNetwork)
                        }
                    }
                    }

                MyNavigation(sharedPrefs , databaseMenuItems)


                }
            }
        }
    }







@Composable
fun MyNavigation(sharedPrefs : SharedPreferences , databaseMenuItems :  List<MenuItemRoom> ) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScaffoldScreen.route){
        composable(ScaffoldScreen.route){
            ScaffoldComposable(
                sharedPrefs = sharedPrefs ,
                onNavigateToDesiredScreen= {navController.navigate(ScaffoldScreen.route){popUpTo(ScaffoldScreen.route) { inclusive = true }}},
                onNavigateToProfileScreen =  {navController.navigate(ProfileScreen.route)},
                databaseMenuItems=databaseMenuItems
            )
        }
        composable(OnboardingScreen.route){
            OnboardingScreen(sharedPrefs = sharedPrefs ,
                onNavigateToWelcomeScreen = {navController.navigate(ScaffoldScreen.route){popUpTo(ScaffoldScreen.route) { inclusive = true }} })
        }
        composable(WelcomeScreen.route){
            HomeScreen(sharedPrefs = sharedPrefs ,
                onNavigateToBoardingScreen= {navController.navigate(ScaffoldScreen.route){popUpTo(ScaffoldScreen.route) { inclusive = true }} } ,
                databaseMenuItems=databaseMenuItems )
        }
        composable(ProfileScreen.route){
            ProfileScreen(sharedPrefs = sharedPrefs ,  onNavigateToDesiredScreen= {navController.navigate(ScaffoldScreen.route){popUpTo(ScaffoldScreen.route) { inclusive = true }}},)
        }

        }


    }



