package com.example.littlelemoncapstone

import android.content.SharedPreferences
import android.util.Log
import android.widget.ScrollView
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.widget.NestedScrollView

@Composable
fun HomeScreen(sharedPrefs : SharedPreferences, onNavigateToBoardingScreen:()->Unit ,databaseMenuItems: List<MenuItemRoom>)
{
    var searchKey by remember   { mutableStateOf("") }
    val onSearchKeyChange = { newSearchKey:String -> searchKey = newSearchKey  }

    var selectedCategory by remember   { mutableStateOf("") }
    val onCategorySelected = { category : String -> selectedCategory = category  }

    var selectedDishes = if (selectedCategory.isNotBlank()) {
        databaseMenuItems.filter { it.category == selectedCategory }
    } else {
        databaseMenuItems
    }



    Column(){

        UpperPanel(searchKey=searchKey , onSearchKeyChange = onSearchKeyChange )
        MiddlePanel(onCategorySelected)
        LowerPanel(dishes = selectedDishes , searchKey = searchKey  )
    }

}