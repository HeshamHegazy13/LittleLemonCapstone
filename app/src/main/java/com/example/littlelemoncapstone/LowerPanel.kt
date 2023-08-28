package com.example.littlelemoncapstone

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LowerPanel(dishes: List<MenuItemRoom> , searchKey : String) {
    val filteredDishes = if (searchKey.isNotBlank()) {
        dishes.filter { it.title.contains(searchKey, ignoreCase = true) }
    } else {
        dishes
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp, start = 10.dp)
    ) {
        items(
            count = filteredDishes.size,
            itemContent = { index ->
                MenuDish(dish = filteredDishes[index])
            }
        )
    }
}