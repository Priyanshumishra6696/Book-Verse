package com.example.booksapp.UIElements.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.booksapp.BooksViewModel
import com.example.booksapp.R
import com.example.booksapp.UIElements.screens.BottomNavigationItems
import com.example.booksapp.models.apiModels.Item
import com.example.booksapp.ui.theme.DarkBlue
import com.example.booksapp.ui.theme.DarkYellow
import com.example.booksapp.utils.addHttps

@Composable
fun TopSectionOfBooksScreen(viewModel: BooksViewModel,navController: NavController,item: Item){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp))
            .background(DarkBlue)
    ) {
        //Icons containing Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            IconButton(
                onClick = {
                    navController.navigate(route = BottomNavigationItems.HomeScreen.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        //book image and text containing row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 64.dp)
        ) {
            //contains image
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(start = 16.dp)
            ) {
                if (item.volumeInfo.imageLinks != null) {
                    AsyncImage(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth(0.4f),
                        model = addHttps(item.volumeInfo.imageLinks.thumbnail),
                        contentDescription = null
                    )
                } else {
                    Image(
                        painter = painterResource(R.drawable.noimageavaibleimage),
                        contentDescription = null
                    )
                }
            }

            //contains Rest stuff in row Text ,  rating , all that
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(end = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier,
                        text = item.volumeInfo.title,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = DarkYellow
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item.volumeInfo.averageRating.toString(),
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {

                        val url = if(item.volumeInfo.infoLink!=null && item.volumeInfo.infoLink!=""){item.volumeInfo.infoLink}else{"https://www.google.com"}
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                ) {
                    Text(
                        text = "View"
                    )
                }
                Button(
                    onClick = {
                        viewModel.showPopUp=true
                        viewModel.message = "Book Added"
                        viewModel.addBook(item)
                    }
                ) {
                    Text(
                        text = "Save"
                    )
                }

            }

        }

    }
}