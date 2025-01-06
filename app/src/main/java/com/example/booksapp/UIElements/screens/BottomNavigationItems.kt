package com.example.booksapp.UIElements.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItems(
    val route: String,
    val title: String,
    val FilledIcon: ImageVector,
    val OutlinedIcon: ImageVector
) {
    object HomeScreen : BottomNavigationItems(
        route = "HomeScreen",
        title = "Home" ,
        FilledIcon = Icons.Filled.Home,
        OutlinedIcon = Icons.Outlined.Home
    )
    object SearchScreen : BottomNavigationItems(
        route = "SearchScreen",
        title = "Search",
        FilledIcon = Icons.Filled.Search,
        OutlinedIcon = Icons.Outlined.Search
    )
    object NotificationsScreen : BottomNavigationItems(
        route = "NotificationScreen",
        title = "Notifications",
        FilledIcon = Icons.Filled.Notifications,
        OutlinedIcon = Icons.Outlined.Notifications,
    )
    object MyBooksScreen : BottomNavigationItems(
        route = "MyBooksScreen",
        title = "My Books",
        FilledIcon = Icons.Filled.Menu,
        OutlinedIcon = Icons.Outlined.Menu
    )
}