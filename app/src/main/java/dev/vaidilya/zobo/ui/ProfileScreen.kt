package dev.vaidilya.zobo.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.vaidilya.zobo.R

@Composable
fun ProfileScreen(modifier: Modifier) {

    Column(
        modifier=modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
//            modifier= Modifier.size(32.dp),
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Picture"
        )
        Text(
            modifier=Modifier.padding(8.dp),
            text = "Vaidilya Shribhate",
            style = MaterialTheme.typography.titleLarge
        )
    }

}