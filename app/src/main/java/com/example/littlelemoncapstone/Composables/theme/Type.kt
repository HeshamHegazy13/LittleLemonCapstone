package com.example.littlelemoncapstone.Composables.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color = LittleLemonColor.charcoal
    ),
    h2 = TextStyle(
        color = LittleLemonColor.charcoal,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    ),
    body1 = TextStyle(
        color = LittleLemonColor.green,
        fontSize = 19.sp,
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Bold,
        color = LittleLemonColor.green
    ),
    button = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )
)