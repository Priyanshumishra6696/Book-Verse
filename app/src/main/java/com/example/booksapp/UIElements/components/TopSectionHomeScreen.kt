package com.example.booksapp.UIElements.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopSectionHomeScreen(){
    Column(
        modifier = Modifier
            .padding(start = 16.dp, top = 24.dp)
    ) {
        Row {
            Text(
                text = "Hi Fela",
                color = Color.Black,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row {
            Text(
                text = "What are you reading today ?",
                color = Color.Gray
            )
        }
    }
}