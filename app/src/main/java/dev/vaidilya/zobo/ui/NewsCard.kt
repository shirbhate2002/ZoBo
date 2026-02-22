package dev.vaidilya.zobo.ui.theme


import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import dev.vaidilya.zobo.R
import dev.vaidilya.zobo.models.User
import dev.vaidilya.zobo.models.articlesItem
import dev.vaidilya.zobo.utils.Constants


@Composable
fun ArticleCard(
    articlesItem: articlesItem,
    navController: NavController,
    onItemClick: (Int) -> Unit
){
    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable{},
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.cardElevation(8.dp),
        onClick = {
            onItemClick(articlesItem.id)
        }
    ){
        Row {
            ShimmerAsyncImage(Modifier.size(128.dp),articlesItem.cover_image)
            Column(
                modifier = Modifier.height(128.dp)
                    .padding(8.dp)
            ){
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = articlesItem.title,
                    style= MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1.0f))
                WriterSection(
                    articlesItem.user,
                    navController
                )
            }
        }
    }
}

@Composable
fun WriterSection(
    user: User,
    navController: NavController
){
    Row(
        modifier = Modifier
    ){
        AsyncImage(
            model = user.profile_image,
            contentDescription = "${user.name}-profile-picture",
            modifier = Modifier.padding(start = 8.dp, end = 8.dp).size(32.dp).clip(RoundedCornerShape(32.dp))
                .clickable{
                    navController.navigate("Profile"+"/${user.username}")
                },
        )
        Column(
        ){
            Text(
                text = user.name,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = user.username,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.weight(1.0f))
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.outline_bookmark_24),
            contentDescription = "Bookmark",
            alignment = Alignment.BottomEnd
        )
    }
}

@Composable
fun ShimmerAsyncImage(modifier: Modifier,imageUrl: String?=null) {

    var isLoading = remember { mutableStateOf(true) }

    Box(
        modifier = modifier
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

        val fallbackImageUrl="https://fatstacksblog.com/wp-content/uploads/2019/11/Person-writing-article-nov26.jpg"

        AsyncImage(
            model = imageUrl ?: fallbackImageUrl,
            contentDescription = null,
            modifier = modifier,
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