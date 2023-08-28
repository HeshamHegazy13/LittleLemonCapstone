package com.example.littlelemoncapstone


import android.content.SharedPreferences
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun OnboardingScreen(sharedPrefs : SharedPreferences,onNavigateToWelcomeScreen:()->Unit) {
    var firstName by remember   {mutableStateOf("")}
    var lastName  by remember   {mutableStateOf("")}
    var email     by remember   {mutableStateOf("")}
    var showError by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {


        Text(
            text = "Let's get to know you",
            color = colorResource(R.color.littleLemon_offWhite),
            fontSize = 30.sp,
            modifier = Modifier
                .background(color = colorResource(R.color.littleLemon_green))
                .padding(50.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,


            )
        Text(
            text = "Personal information",
            color = Color.Black,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ){
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = {firstName = it},
                    label = { Text(text = "first name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    shape = RoundedCornerShape(15.dp),


                )
                OutlinedTextField(
                    value = lastName,
                    onValueChange = {lastName=it},
                    label = { Text(text = "last name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    shape = RoundedCornerShape(15.dp),
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = {email=it},
                    label = { Text(text = "email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    shape = RoundedCornerShape(15.dp),
                )


            }

            if (showError) {
                Text(
                    text = "Complete All of the Fields, Please",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp),
                    textAlign = TextAlign.Center
                )
            }

            Button(
                onClick =

                    {
                        if (validateInputs(firstName, lastName, email)) {
                            sharedPrefs.edit().putBoolean("auth",true).putString("first_name",firstName).putString("last_name",lastName).putString("email",email).apply()
                            Toast.makeText(context,"Registration successful!",LENGTH_LONG).show()
                            onNavigateToWelcomeScreen()
                        } else {
                            showError = true
                            Toast.makeText(context,"Registration unsuccessful. Please enter all data !",LENGTH_LONG).show()
                        }
                    }
                    ,

                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.littleLemon_yellow)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),

                shape = RoundedCornerShape(10.dp),


                ) {
                Text(
                    text = "Register",
                    color = colorResource(id = R.color.littleLemon_offGrey)
                )
            }


        }
    }
}


private fun validateInputs(firstName: String, lastName: String, email: String): Boolean {
    return firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()
}