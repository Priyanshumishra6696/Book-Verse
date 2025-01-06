package com.example.booksapp.UIElements.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.booksapp.BooksViewModel
import com.example.booksapp.R
import com.example.booksapp.models.apiModels.Item
import com.example.booksapp.utils.addHttps

@Composable
fun BookSlot(navController: NavController,item: Item,viewModel: BooksViewModel){
    Column(
        modifier = Modifier
            .width(160.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                viewModel.updatecurrItem(item)
                navController.navigate("BookScreen")
            }
//            .border(BorderStroke(2.dp, color = Color.Black), shape = RoundedCornerShape(16.dp))
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.clip(RoundedCornerShape(16.dp)),
            horizontalArrangement = Arrangement.Start
        ) {
            if(item.volumeInfo.imageLinks==null){
                Image(
                    painter = painterResource(R.drawable.noimageavaibleimage),
                    contentDescription = null
                )
            }else{
                AsyncImage(
                    modifier = Modifier.size(160.dp),
                    model = addHttps(item.volumeInfo.imageLinks.thumbnail),
                    contentDescription = null,
                    error = painterResource(R.drawable.pic)
                )
            }

        }
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
        ) {
            Text(
                text = item.volumeInfo.title,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            if(item.volumeInfo.authors==null){
                Text(
                    text = "Error"
                )
            }else{
                Text(
                    text = item.volumeInfo.authors[0]
                )
            }

        }
        Row {
            
        }

    }
}