package com.example.booksapp.UIElements.components.HorizontalScrollableSections

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.booksapp.BooksViewModel
import com.example.booksapp.UIElements.components.BookSlot
import com.example.booksapp.api.NetworkResponse

@Composable
fun FictionSectionScrollableRow(navController: NavController,viewModel: BooksViewModel){
    val booksearchResult by viewModel.getBooksForSection("Fiction").observeAsState()
    when(val result = booksearchResult){
        is NetworkResponse.Error -> {
            Text(text = "Error")
        }
        is NetworkResponse.Loading -> {
            CircularProgressIndicator()
        }
        is NetworkResponse.Success -> {
            LazyRow {
                result.data.items.forEachIndexed { index, item ->
                    if(item.volumeInfo.imageLinks!=null){
                        item {
                            Spacer(modifier = Modifier.width(8.dp))
                            BookSlot(navController,item = item,viewModel)
                        }
                    }

                }
            }
        }
        null -> {}
    }
}
