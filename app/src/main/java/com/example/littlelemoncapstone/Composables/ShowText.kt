package com.example.littlelemoncapstone.Composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemoncapstone.R

@Composable
fun ShowText(header:String , info:String){

  Column() {
      Text(text = header ,fontSize = 25.sp, fontWeight = FontWeight.Bold,color= colorResource(id = R.color.littleLemon_offWhite) )
      TextField(
          value = info,
          onValueChange = {},
          enabled = false ,
          colors = textFieldColors(backgroundColor = colorResource(R.color.littleLemon_offWhite)) ,
          textStyle= TextStyle(
              fontSize=calculateTextSize(info),
              color = colorResource(R.color.littleLemon_offGrey)) ,
          modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(15.dp)),
          singleLine=true,
      )
  }
}

private fun calculateTextSize(text: String): TextUnit {
    return if (text.length > 10) {
        20.sp // Decrease font size for longer text
    } else {
        30.sp // Default font size
    }
}