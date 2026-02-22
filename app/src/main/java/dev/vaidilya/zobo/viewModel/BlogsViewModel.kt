package dev.vaidilya.zobo.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vaidilya.zobo.repository.BlogsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogsViewModel @Inject constructor(private val blogsRepository: BlogsRepository) : ViewModel() {

    val articlesLiveData get() = blogsRepository.products
    val detailedArticleLiveData get() = blogsRepository.detailedArticle

    init{
        viewModelScope.launch {
            blogsRepository.getArticles()
        }
    }

    suspend fun getDetailedArticle(id: Int){
        viewModelScope.launch {
            blogsRepository.getFullArticle(id)
        }
    }

}