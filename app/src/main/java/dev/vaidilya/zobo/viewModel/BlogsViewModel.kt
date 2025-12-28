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

    init{
        viewModelScope.launch {
            blogsRepository.getArticles()
        }
    }

    fun loadMoreArticles(endCursor: String?) {
        viewModelScope.launch {
            blogsRepository.getArticles(endCursor)
        }
    }

}