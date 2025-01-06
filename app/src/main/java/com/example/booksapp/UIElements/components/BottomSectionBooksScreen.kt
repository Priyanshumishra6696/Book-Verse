package com.example.booksapp.UIElements.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.booksapp.BooksViewModel
import com.example.booksapp.models.apiModels.Item

@Composable
fun BottomSectionBooksScreen(viewModel: BooksViewModel,navController: NavController,item: Item){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //Description
        Column {
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Description",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = if(item.volumeInfo.description!=null){item.volumeInfo.description}else{"NA"},
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )
            }
        }

        //About the Book
        Column{
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "About The Book",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .border(width = 2.dp, color = Color.Gray),
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                //length : 674 pages
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null
                    )
                    Text(
                        text = "Length : ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if(item.volumeInfo.pageCount != null){item.volumeInfo.pageCount.toString() + " pages"}else{"NA"},
                        fontSize = 16.sp
                    )
                }
                //language : English
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null
                    )
                    Text(
                        text = "Language : ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if(item.volumeInfo.language!=null){if(item.volumeInfo.language=="en"){"English"}else{item.volumeInfo.language}}else{"NA"},
                        fontSize = 16.sp
                    )
                }
                //maturity rating
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null
                    )
                    Text(
                        text = "Maturity Rating : ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if(item.volumeInfo.maturityRating!=null){if(item.volumeInfo.maturityRating=="NOT_MATURE"){"Not mature"}else{"Mature"}}else{"NA"},
                        fontSize = 16.sp
                    )
                }
                //categories
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null
                    )
                    Text(
                        text = "Category : ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if(item.volumeInfo.categories!=null){
                        Text(
                            text = item.volumeInfo.categories[0],
                            fontSize = 16.sp
                        )
                    }else{
                        Text(
                            text = "NA",
                            fontSize = 16.sp
                        )
                    }

                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Column(
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            //product details
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Product Details",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            //publisher
            Row(
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "Publisher : ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if(item.volumeInfo.publisher!=null){item.volumeInfo.publisher}else{"NA"},
                    fontSize = 16.sp
                )
            }
            //published date
            Row(
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "Published Date : ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if(item.volumeInfo.publishedDate!=null){item.volumeInfo.publishedDate}else{"NA"},
                    fontSize = 16.sp
                )
            }
            //print type
            Row(
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "Print Type : ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if(item.volumeInfo.printType!=null){item.volumeInfo.printType}else{"NA"},
                    fontSize = 16.sp
                )
            }

            //content version
            Row(
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "Content version : ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if(item.volumeInfo.contentVersion!=null){item.volumeInfo.contentVersion}else{"NA"},
                    fontSize = 16.sp
                )
            }

        }

    }
}