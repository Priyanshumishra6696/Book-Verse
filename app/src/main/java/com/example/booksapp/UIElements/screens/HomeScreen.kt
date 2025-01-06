package com.example.booksapp.UIElements.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.booksapp.BooksViewModel
import com.example.booksapp.UIElements.components.BottomNavigationBar
import com.example.booksapp.UIElements.components.HorizontalScrollableSections.ComicsSectionScrollableRow
import com.example.booksapp.UIElements.components.HorizontalScrollableSections.FictionSectionScrollableRow
import com.example.booksapp.UIElements.components.HorizontalScrollableSections.NonFictionSectionScrollableRow
import com.example.booksapp.UIElements.components.HorizontalScrollableSections.PhilosophySectionScrollableRow
import com.example.booksapp.UIElements.components.HorizontalScrollableSections.ReligiousSectionScrollableRow
import com.example.booksapp.UIElements.components.HorizontalScrollableSections.ScienceSectionScrollableRow
import com.example.booksapp.UIElements.components.TopSectionHomeScreen

@Composable
fun HomeScreen(viewModel: BooksViewModel,navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 32.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    TopSectionHomeScreen()
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Text(
                        modifier = Modifier.padding(top = 16.dp, start = 16.dp , bottom = 16.dp ),
                        text = "Science",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    ScienceSectionScrollableRow(navController,viewModel)
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Text(
                        modifier = Modifier.padding(top = 16.dp, start = 16.dp , bottom = 16.dp ),
                        text = "Fiction",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    FictionSectionScrollableRow(navController,viewModel)
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Text(
                        modifier = Modifier.padding(top = 16.dp, start = 16.dp , bottom = 16.dp ),
                        text = "NonFiction",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    NonFictionSectionScrollableRow(navController,viewModel)
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Text(
                        modifier = Modifier.padding(top = 16.dp, start = 16.dp , bottom = 16.dp ),
                        text = "Philosophy",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    PhilosophySectionScrollableRow(navController,viewModel)
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Text(
                        modifier = Modifier.padding(top = 16.dp, start = 16.dp , bottom = 16.dp ),
                        text = "Religious",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    ReligiousSectionScrollableRow(navController,viewModel)
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Text(
                        modifier = Modifier.padding(top = 16.dp, start = 16.dp , bottom = 16.dp ),
                        text = "Comics",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    ComicsSectionScrollableRow(navController,viewModel)
                    Spacer(modifier = Modifier.height(16.dp))
                }

            }
        }
        BottomNavigationBar(navController)
    }
}

