package com.example.booksapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.booksapp.BooksViewModel
import com.example.booksapp.UIElements.screens.BookScreen
import com.example.booksapp.UIElements.screens.BottomNavigationItems
import com.example.booksapp.UIElements.screens.HomeScreen
import com.example.booksapp.UIElements.screens.MyBooksScreen
import com.example.booksapp.UIElements.screens.NotificationsScreen
import com.example.booksapp.UIElements.screens.SearchScreen

@Composable
fun MyAppNavigation(viewModel: BooksViewModel){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "HomeScreen",
        builder = {
            composable(route = BottomNavigationItems.HomeScreen.route){
                HomeScreen(viewModel,navController)
            }
            composable(route = BottomNavigationItems.SearchScreen.route){
                SearchScreen(viewModel,navController)
            }
            composable(route = BottomNavigationItems.NotificationsScreen.route){
                NotificationsScreen(viewModel,navController)
            }
            composable(route = BottomNavigationItems.MyBooksScreen.route){
                MyBooksScreen(viewModel,navController)
            }
            composable(route = "BookScreen"){
                viewModel.getcurrItem()?.let { it1 -> BookScreen(viewModel,navController, it1) }
            }
        }
    )
}