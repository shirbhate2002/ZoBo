package dev.vaidilya.zobo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dev.vaidilya.zobo.ui.theme.ArticleCard
import dev.vaidilya.zobo.viewModel.BlogsViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val viewModel = hiltViewModel<BlogsViewModel>()
    val products = viewModel.articlesLiveData.observeAsState()

    val listState = rememberLazyListState()
    val productsList = products.value?.data?.posts?.nodes.orEmpty()
    val endCursor = products.value?.data?.posts?.pageInfo?.endCursor

    LaunchedEffect(listState.firstVisibleItemIndex, productsList.size) {
        if (
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == productsList.lastIndex
        ) {
            viewModel.loadMoreArticles(endCursor)
        }
    }

    LazyColumn(
        state = listState,
        modifier = modifier,
        contentPadding= PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = products.value?.data?.posts?.nodes.orEmpty()
        ) { product ->
            ArticleCard(
                url = product.featuredImage.node.mediaItemUrl,
                title = product.title,
                onClick={
                    navController.navigate("Article"+"/${product.uri.replace("/","")}")
                }
            )
        }
    }
}