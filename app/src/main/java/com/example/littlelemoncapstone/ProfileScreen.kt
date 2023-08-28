package com.example.littlelemoncapstone

import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemoncapstone.Composables.ShowText

@Composable
fun ProfileScreen(sharedPrefs:SharedPreferences , onNavigateToDesiredScreen :()->Unit){
    val firstName = sharedPrefs.getString("first_name","").toString()
    val lastName = sharedPrefs.getString("last_name","").toString()
    val email = sharedPrefs.getString("email","").toString()

    Column(
        modifier= Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.littleLemon_green)),
        verticalArrangement= Arrangement.SpaceAround,
        horizontalAlignment= Alignment.CenterHorizontally,
    ) {

        Text(text = "Personal Information" , fontSize = 35.sp,color= colorResource(id = R.color.littleLemon_offWhite) )
        Column(
            horizontalAlignment= Alignment.Start,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 65.dp)
            .fillMaxHeight(0.70f) ,
            ) {
            ShowText(header = "FIRST NAME", info = firstName)
            ShowText(header ="LAST NAME", info = lastName)
            ShowText(header ="EMAIL", info = email)

        }
        Button(onClick = {
            sharedPrefs.edit().clear().apply()
            onNavigateToDesiredScreen() } ,
            colors=buttonColors( backgroundColor= colorResource(R.color.littleLemon_yellow )
            )
        ) {
            Text(text = "Sign out" ,fontSize = 20.sp )

        }
    }

}