package com.example.littlelemoncapstone


import android.content.SharedPreferences
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun TopAppBar(sharedPrefs : SharedPreferences , onNavigateToProfileScreen:()->Unit)
{
    var isAuth  = sharedPrefs.getBoolean("auth", false)
    val context = LocalContext.current
    
    
   Row(
       horizontalArrangement = Arrangement.SpaceBetween,
       verticalAlignment= Alignment.CenterVertically,
       modifier = Modifier.height(80.dp)
   ) {
       IconButton(onClick = { Toast.makeText(context,"ToDrawer",LENGTH_LONG).show() }) {
           Icon(Icons.Filled.Menu, contentDescription = "Favorite")
       }
       Image(
           painter = painterResource(id = R.drawable.logo),
           contentDescription = "Logo Image",
           modifier = Modifier

               .height(50.dp).fillMaxWidth(0.8f),
           alignment = Alignment.Center
       )
       if (isAuth){
           IconButton(onClick = {
               Toast.makeText(context,"ToProfilePage",LENGTH_LONG).show()
               onNavigateToProfileScreen()
           },
           modifier=Modifier.padding(end = 10.dp)) {
               Image(
                   painter = painterResource(id = R.drawable.profile),
                   contentDescription = "Profile Image",
                   modifier = Modifier
                       .width(50.dp)
                       .height(50.dp),
                   alignment = Alignment.Center
               )
           }
       }
   }
}