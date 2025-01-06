package com.example.booksapp.UIElements.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.booksapp.BooksViewModel
import com.example.booksapp.UIElements.components.BottomNavigationBar

@Composable
fun  NotificationsScreen(viewModel: BooksViewModel,navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "notification screen")
        }
        BottomNavigationBar(navController)
    }
}