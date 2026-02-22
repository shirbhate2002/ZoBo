package dev.vaidilya.zobo.ui

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Composable
fun BookMarkScreen(modifier: Modifier = Modifier) {

    Column(
        modifier=modifier.fillMaxSize()
    ){
        Text("My Fav")
    }
}