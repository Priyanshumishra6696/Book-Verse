package com.example.booksapp.UIElements.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.booksapp.BooksViewModel
import com.example.booksapp.R
import com.example.booksapp.UIElements.components.BookRow
import com.example.booksapp.UIElements.components.BookSlot
import com.example.booksapp.UIElements.components.BottomNavigationBar
import com.example.booksapp.api.NetworkResponse
import com.example.booksapp.ui.theme.WhitishGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: BooksViewModel,navController: NavController){
    val booksearchResult by viewModel.booksResult.observeAsState()
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 40.dp)
        ) {
            LazyColumn {
                item {
                    Row(
                        modifier = Modifier
                    ) {
                        TextField(
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search icon"
                                )
                            },
                            label = {
                                Text("Search")
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedBorderColor = WhitishGray,
                                focusedBorderColor = WhitishGray,

                                ),
                            modifier = Modifier
                                .padding(16.dp)
                                .clip(RoundedCornerShape(24.dp))
                                .background(WhitishGray),
                            value = text,
                            onValueChange = {
                                text=it
                            }
                        )
                        Button(
                            modifier = Modifier.padding(top = 16.dp),
                            onClick = {
                                viewModel.getData(text)
                            },
                            colors = ButtonDefaults.buttonColors(Color(0, 123, 250))
                        ) {
                            Text("Search")
                        }
                    }
                }
                item{
                    if(booksearchResult==null){
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.nodatafound),
                                contentDescription = null
                            )
                        }
                    }else{
                        when(val result = booksearchResult){
                            is NetworkResponse.Error -> {

                            }
                            NetworkResponse.Loading -> {
                                Column(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                            is NetworkResponse.Success -> {
                                if(result.data.items.isNullOrEmpty()){

                                }else{
                                    result.data.items.forEachIndexed { index, item ->
                                        if(item.volumeInfo.imageLinks!=null){
                                            Spacer(modifier = Modifier.height(8.dp))
                                            BookRow(viewModel,item,navController)
                                        }

                                    }
                                }
                            }
                            null -> {}
                        }
                    }

                }

            }

        }
        BottomNavigationBar(navController)
    }
}