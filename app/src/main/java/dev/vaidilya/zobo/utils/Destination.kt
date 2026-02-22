package dev.vaidilya.zobo.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavDestination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    HOME(Destination.Home.route, "Home", Icons.Outlined.Home, "Home"),
    BOOKMARK(Destination.Bookmark.route, "Saved", Icons.Outlined.FavoriteBorder, "Saved"),
    PROFILE(Destination.Profile.route, "Profile", Icons.Outlined.AccountCircle, "Profile"),
}


enum class Destination(
    val route: String
){
    Home("home"),
    Bookmark("saved"),
    Profile("Profile"),
    Article("article/{id}")
}