package com.example.booksapp.UIElements.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.booksapp.BooksViewModel
import com.example.booksapp.R
import com.example.booksapp.UIElements.components.BookRowForMyBooksScreen
import com.example.booksapp.UIElements.components.BottomNavigationBar
import com.example.booksapp.UIElements.components.BottomPopUp

@Composable
fun MyBooksScreen(viewModel: BooksViewModel,navController: NavController){
    val savedBooks by viewModel.savedBooksList.observeAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.weight(1f).padding(top = 40.dp)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "My Books",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Bookmark to add books",
                            color = Color.Gray
                        )
                    }
                }
                item {
                    if(savedBooks?.isEmpty() == true){
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                modifier = Modifier.clip(RoundedCornerShape(16.dp)),
                                painter = painterResource(R.drawable.nodatafound),
                                contentDescription = null
                            )
                            Text(
                                text = "No Books to Display",
                                color = Color.Gray,
                                fontSize = 20.sp
                            )
                        }
                    }
                    savedBooks?.forEachIndexed { index, booksEntity ->
                        Spacer(modifier = Modifier.height(16.dp))
                        BookRowForMyBooksScreen(
                            navController = navController,
                            viewModel = viewModel,
                            link = booksEntity.link,
                            title = booksEntity.booksname,
                            authors = booksEntity.authors,
                            rating = booksEntity.averageRating,
                            id = booksEntity.id,
                            booksEntity = booksEntity
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
            ) {
                BottomPopUp(viewModel)
            }
        }
        BottomNavigationBar(navController)
    }
}