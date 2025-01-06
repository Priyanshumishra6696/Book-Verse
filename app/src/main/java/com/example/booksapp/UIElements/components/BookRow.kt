package com.example.booksapp.UIElements.components

import androidx.annotation.Nullable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.booksapp.BooksViewModel
import com.example.booksapp.R
import com.example.booksapp.db.StoreBooks.BooksEntity
import com.example.booksapp.models.apiModels.AccessInfo
import com.example.booksapp.models.apiModels.Epub
import com.example.booksapp.models.apiModels.ImageLinks
import com.example.booksapp.models.apiModels.Item
import com.example.booksapp.models.apiModels.ListPrice
import com.example.booksapp.models.apiModels.Offer
import com.example.booksapp.models.apiModels.PanelizationSummary
import com.example.booksapp.models.apiModels.Pdf
import com.example.booksapp.models.apiModels.ReadingModes
import com.example.booksapp.models.apiModels.RetailPriceX
import com.example.booksapp.models.apiModels.SaleInfo
import com.example.booksapp.models.apiModels.SearchInfo
import com.example.booksapp.models.apiModels.VolumeInfo
import com.example.booksapp.ui.theme.DarkYellow
import com.example.booksapp.ui.theme.WhitishGray
import com.example.booksapp.utils.addHttps

@Composable
fun BookRow(viewModel: BooksViewModel,item: Item,navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                viewModel.updatecurrItem(item)
                navController.navigate("BookScreen")
            }
    ) {
        Column(
            modifier = Modifier
        ) {
            if(item.volumeInfo.imageLinks==null){
                Image(
                    modifier = Modifier.size(120.dp),
                    painter = painterResource(R.drawable.noimageavaibleimage),
                    contentDescription = null
                )
            }else{
                AsyncImage(
                    modifier = Modifier.size(120.dp),
                    model = addHttps(item.volumeInfo.imageLinks.thumbnail),
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.weight(1f)) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = DarkYellow
                    )
                    Text(
                        text = item.volumeInfo.averageRating.toString()
                    )
                }
//                if(viewModel.checkIfBookisSaved(item)){
//                    Icon(
//                        modifier = Modifier
//                            .padding(end = 32.dp)
//                            .clickable {
//                                viewModel.addBook(item = item)
//                            },
//                        imageVector = Icons.Outlined.Bookmark,
//                        contentDescription = null
//                    )
//                }else{
//                    Icon(
//                        modifier = Modifier
//                            .padding(end = 32.dp)
//                            .clickable {
//                                viewModel.addBook(item = item)
//                            },
//                        imageVector = Icons.Outlined.BookmarkBorder,
//                        contentDescription = null
//                    )
//                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = item.volumeInfo.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.volumeInfo.authors[0],
                fontSize = 15.sp,
                color = Color.Gray
            )
        }

    }

}


@Composable
fun BookRowForMyBooksScreen(navController: NavController,viewModel: BooksViewModel,link : String,title : String,authors : String,rating : String,id:Int,booksEntity: BooksEntity){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
//            viewModel.deleteBook(id = id)
                viewModel.updatecurrItem(getItemFrombookEntity(booksEntity))
                navController.navigate("BookScreen")
            }
    ) {
        Column(
            modifier = Modifier
        ) {
            if(link==""){
                Image(
                    modifier = Modifier.size(120.dp),
                    painter = painterResource(R.drawable.noimageavaibleimage),
                    contentDescription = null
                )
            }else{
                AsyncImage(
                    modifier = Modifier.size(120.dp),
                    model = addHttps(link),
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.weight(1f)) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = DarkYellow
                    )
                    Text(
                        text = rating
                    )
                }
                Icon(
                    modifier = Modifier.padding(end = 32.dp).clickable {
                        viewModel.showPopUp = true
                        viewModel.message = "Book Deleted"
                        viewModel.deleteBook(id)
                    },
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = authors,
                fontSize = 15.sp,
                color = Color.Gray
            )
        }

    }
}

fun getItemFrombookEntity(booksEntity: BooksEntity) : Item{
    return Item(
        accessInfo = AccessInfo(
            accessViewStatus = "",
            country = "",
            embeddable = false,
            epub = Epub(
                acsTokenLink = "",
                isAvailable = false
            ),
            pdf = Pdf(
                acsTokenLink = "",
                isAvailable = false
            ),
            publicDomain = false,
            quoteSharingAllowed = false,
            textToSpeechPermission = "",
            viewability = "",
            webReaderLink = ""
        ),
        etag = "",
        id = booksEntity.uniqueId,
        kind = "",
        saleInfo = SaleInfo(
            buyLink = "",
            country = "",
            isEbook = false,
            listPrice = ListPrice(
                amount = 0.0,
                currencyCode = ""
            ),
            offers = emptyList(),
            retailPrice = RetailPriceX(
                amount = 0.0,
                currencyCode = ""
            ),
            saleability = ""
        ) ,
        searchInfo = SearchInfo(
            textSnippet = ""
        ),
        selfLink = "",
        volumeInfo = VolumeInfo(
            allowAnonLogging = false,
            authors = listOf(booksEntity.authors),
            averageRating = booksEntity.averageRating.toDouble(),
            canonicalVolumeLink = "",
            categories = listOf(booksEntity.category),
            contentVersion = if(booksEntity.contentVersion!=null){booksEntity.contentVersion}else{"NA"},
            description = if(booksEntity.description!=null){booksEntity.description}else{"NA"},
            imageLinks = ImageLinks(
                smallThumbnail = "",
                thumbnail = booksEntity.link
            ),
            industryIdentifiers = emptyList(),
            infoLink = if(booksEntity.infolink!="" && booksEntity.infolink!=null){booksEntity.infolink}else{""},
            language = if(booksEntity.language!=null){booksEntity.language}else{"NA"},//if(!=null){}else{"NA"}
            maturityRating = if(booksEntity.maturityRating!=null){booksEntity.maturityRating}else{"NA"},
            pageCount = if(booksEntity.pageCount!=null){booksEntity.pageCount}else{0},
            panelizationSummary = PanelizationSummary(
                containsEpubBubbles = false,
                containsImageBubbles = false
            ),
            previewLink = "",
            printType = if(booksEntity.printType!=null){booksEntity.printType}else{"NA"},
            publishedDate = if(booksEntity.publishedDate!=null){booksEntity.publishedDate}else{"NA"},
            publisher = if(booksEntity.publisher!=null){booksEntity.publisher}else{"NA"},
            ratingsCount = 0 ,
            readingModes = ReadingModes(
                image = false,
                text = false
            ),
            subtitle = "",
            title = booksEntity.booksname
        )
    )
}