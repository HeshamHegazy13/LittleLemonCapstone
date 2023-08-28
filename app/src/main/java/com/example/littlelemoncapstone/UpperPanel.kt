package com.example.littlelemoncapstone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemoncapstone.Composables.theme.LittleLemonColor

@Composable
fun UpperPanel(searchKey : String ,onSearchKeyChange :  (String) -> Unit) {

    Column(
        modifier = Modifier
            .background(LittleLemonColor.green)
            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.title),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = LittleLemonColor.yellow
        )
        Row{
            Column(modifier = Modifier
                .weight(1f)
                .height(150.dp) , verticalArrangement = Arrangement.SpaceBetween ){
                Text(
                    text = stringResource(id = R.string.location),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = LittleLemonColor.cloud
                )
                Text(
                text = stringResource(id = R.string.description),
                style = MaterialTheme.typography.body1,
                color = LittleLemonColor.cloud,
            )}
    Image(
        painter = painterResource(id = R.drawable.heroimage),
        contentDescription = "Upper Panel Image",
        modifier = Modifier
            .height(150.dp)
            .padding(top = 10.dp)
            .clip(RoundedCornerShape(20.dp))
    )


        }
        OutlinedTextField(
            value = searchKey,
            onValueChange = onSearchKeyChange,
            label = { Text(text = "Search" , color = LittleLemonColor.cloud) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            shape = RoundedCornerShape(15.dp),
            colors= textFieldColors(backgroundColor = Color.Gray),
            textStyle = TextStyle(color = Color.Black , fontSize = 20.sp ),
            leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") }




        )
    }

}


