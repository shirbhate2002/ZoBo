package dev.vaidilya.zobo.ui

sealed class Destinations(val route: String) {
    object Home: Destinations("Home")
    object Article: Destinations("Article/{url}")
}