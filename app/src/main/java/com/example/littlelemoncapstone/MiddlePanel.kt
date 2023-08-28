package com.example.littlelemoncapstone

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

private val pillsInfo = mutableSetOf<String>("Starters","Mains","Desserts")

@Composable
fun MiddlePanel(onCategorySelected: (String) -> Unit){

    Column (Modifier.padding(start=10.dp)){
        Text("Order for Deliver",style= MaterialTheme.typography.h1)
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(top=10.dp)
            ,
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
            verticalAlignment = Alignment.Top, ){
            items(count = pillsInfo.size, itemContent = { index ->
                Text(
                    text = pillsInfo.elementAt(index) ,
                    modifier= Modifier
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(size = 16.dp)
                        ).padding(8.dp)
                        .clickable (){  onCategorySelected(pillsInfo.elementAt(index).lowercase() ) }
                )
            })
        }
        Divider(
            modifier = Modifier.padding(top=20.dp),
            thickness = 5.dp,)
    }
}