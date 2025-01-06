package com.example.booksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.booksapp.navigation.MyAppNavigation
import com.example.booksapp.ui.theme.BooksAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        //viewModel instance
        val viewModel = ViewModelProvider(this)[BooksViewModel::class.java]
        viewModel.getScienceBooks()
        viewModel.fetchBooks("Fiction")
        viewModel.fetchBooks(section = "NonFiction")
        viewModel.fetchBooks(section = "Religious")
        viewModel.fetchBooks(section = "Philosophy")
        viewModel.fetchBooks(section = "Religious")
        viewModel.fetchBooks(section = "Comics")

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BooksAppTheme {
                MyAppNavigation(viewModel)
            }
        }
    }
}



