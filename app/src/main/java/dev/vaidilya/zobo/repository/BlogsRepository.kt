package dev.vaidilya.zobo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.vaidilya.zobo.api.BlogsAPI
import dev.vaidilya.zobo.models.GraphQLRequest
import dev.vaidilya.zobo.models.articlesList
import dev.vaidilya.zobo.utils.Constants
import javax.inject.Inject

class BlogsRepository @Inject constructor(private val blogsAPI: BlogsAPI) {

    private val _articles = MutableLiveData<articlesList>()
    val products: LiveData<articlesList>
        get() = _articles


    suspend fun getArticles(endCursor: String? = null) {
        val request = GraphQLRequest(
            query = Constants.POSTS_QUERY,
            variables = mapOf(
                "endCursor" to endCursor,
                "count" to 10
            )
        )

        val response = blogsAPI.getArticles(request)
        if (response.isSuccessful && response.body() != null) {
            val newData = response.body()
            val currentData = _articles.value

            val combinedNodes = if (currentData != null && endCursor != null) {
                currentData.data.posts.nodes + newData!!.data.posts.nodes
            } else {
                newData!!.data.posts.nodes
            }

            val updatedArticlesList = newData!!.copy(
                data = newData.data.copy(
                    posts = newData.data.posts.copy(
                        nodes = combinedNodes
                    )
                )
            )

            _articles.postValue(updatedArticlesList)
            Log.d("TAG", updatedArticlesList.toString())
        } else {
            Log.d("TAG", "Error" + response.errorBody().toString())
        }
    }

}