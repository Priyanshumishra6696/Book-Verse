package com.example.booksapp.UIElements.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.booksapp.UIElements.screens.Constants.screens

@Composable
fun BottomNavigationBar(navController: NavController){
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Black
    ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        screens.forEachIndexed { _, bottomNavigationItems ->
            NavigationBarItem(
                label = {
                    if(currentRoute==bottomNavigationItems.route){
                        Text(text = bottomNavigationItems.title)
                    }
                },
                selected = currentRoute==bottomNavigationItems.route,
                icon = {
                    if(currentRoute==bottomNavigationItems.route){
                        Icon(imageVector = bottomNavigationItems.FilledIcon, contentDescription = null)
                    }else{
                        Icon(imageVector = bottomNavigationItems.OutlinedIcon, contentDescription = null)
                    }
                },
                onClick = {
                    navController.navigate(bottomNavigationItems.route)
                },
                colors = NavigationBarItemColors(
                    selectedIndicatorColor = Color.White,
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Black,
                    disabledIconColor = Color.Black,
                    disabledTextColor = Color.Black,
                )
            )
        }
    }
}