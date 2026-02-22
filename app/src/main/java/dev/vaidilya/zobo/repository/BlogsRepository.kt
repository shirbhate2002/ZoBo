package dev.vaidilya.zobo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.vaidilya.zobo.api.BlogsAPI
import dev.vaidilya.zobo.models.articles
import dev.vaidilya.zobo.models.detailedArticle
import dev.vaidilya.zobo.utils.Constants
import javax.inject.Inject

class BlogsRepository @Inject constructor(private val blogsAPI: BlogsAPI) {

    private val _articles = MutableLiveData<articles>()
    val products: LiveData<articles>
        get() = _articles

    private val _detailedArticle = MutableLiveData<detailedArticle>()
    val detailedArticle: LiveData<detailedArticle>
        get() = _detailedArticle


    suspend fun getArticles() {

        val response = blogsAPI.getArticles()
        if (response.isSuccessful && response.body() != null) {
            val newData = response.body()
            _articles.postValue(newData!!)

            Log.d("TAG", "Api called"+newData.toString())
        } else {
            Log.d("TAG", "Error" + response.errorBody().toString())
        }
    }

    suspend fun getFullArticle(articleId: Int){
        val response = blogsAPI.getFullArticle(articleId)
        if (response.isSuccessful && response.body() != null) {
            val newData = response.body()
            _detailedArticle.postValue(newData!!)
        }
    }

}