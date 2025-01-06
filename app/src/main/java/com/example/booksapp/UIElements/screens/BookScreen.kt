package com.example.booksapp.UIElements.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.booksapp.BooksViewModel
import com.example.booksapp.UIElements.components.BottomPopUp
import com.example.booksapp.UIElements.components.BottomSectionBooksScreen
import com.example.booksapp.UIElements.components.TopSectionOfBooksScreen
import com.example.booksapp.models.apiModels.Item

@Composable
fun BookScreen(viewModel: BooksViewModel,navController: NavController,item: Item){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ){
            item {
                TopSectionOfBooksScreen(viewModel,navController,item)
            }
            item {
                BottomSectionBooksScreen(viewModel,navController,item)
            }
        }
        Column(
            modifier = Modifier.padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
        ) {
            BottomPopUp(viewModel)
        }

    }
}
