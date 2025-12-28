package dev.vaidilya.zobo.ui.theme


import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun ArticleCard(
    url: String,
    title: String,
    description: String
){
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.cardElevation(8.dp)
    ){
        Column {
            ShimmerAsyncImage(url)
            Text(
                modifier = Modifier.padding(8.dp),
                text = title,
                style= MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun ShimmerAsyncImage(imageUrl: String) {

    var isLoading = remember { mutableStateOf(true) }

    Box(
        modifier = Modifier.fillMaxWidth()
            .heightIn(120.dp,180.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        if (isLoading.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .shimmerEffect()
            )
        }
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            onSuccess = { isLoading.value = false },
            contentScale = ContentScale.Crop
        )
    }
}


fun Modifier.shimmerEffect(): Modifier = composed {
    var size = remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition(label = "shimmer")

    val startOffsetX = transition.animateFloat(
        initialValue = -2 * size.value.width.toFloat(),
        targetValue = 2 * size.value.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ),
        label = "shimmer"
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5),
            ),
            start = Offset(startOffsetX.value, 0f),
            end = Offset(startOffsetX.value + size.value.width.toFloat(), size.value.height.toFloat())
        )
    )
    .onGloballyPositioned {
        size.value = it.size
    }
}