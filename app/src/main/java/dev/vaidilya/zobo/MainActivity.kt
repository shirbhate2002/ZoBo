package dev.vaidilya.zobo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.vaidilya.zobo.ui.theme.ArticleCard
import dev.vaidilya.zobo.ui.theme.ZoBoTheme
import dev.vaidilya.zobo.viewModel.BlogsViewModel


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZoBoTheme {
                val viewModel = hiltViewModel<BlogsViewModel>()
                val products = viewModel.articlesLiveData.observeAsState()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val listState = rememberLazyListState()
                    val productsList = products.value?.data?.posts?.nodes.orEmpty()
                    val endCursor = products.value?.data?.posts?.pageInfo?.endCursor
//                    val hasNextPage = products.value?.data?.posts?.pageInfo?.hasNextPage == true

                    LaunchedEffect(listState.firstVisibleItemIndex, productsList.size) {
                        if (
                            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == productsList.lastIndex
                        ) {
                            viewModel.loadMoreArticles(endCursor)
                        }
                    }

                    LazyColumn(
                        state = listState,
                        modifier = Modifier.padding(innerPadding),
                        contentPadding= PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(
                            items = products.value?.data?.posts?.nodes.orEmpty(),
                            key= {product->product.id},
                        ) { product ->
                            ArticleCard(
                                url = product.featuredImage.node.mediaItemUrl,
                                title = product.title,
                                description = product.date
                            )
                        }
                    }
                }
            }
        }
    }
}
