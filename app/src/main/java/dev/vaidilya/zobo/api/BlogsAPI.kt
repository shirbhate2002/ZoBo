package dev.vaidilya.zobo.api

import dev.vaidilya.zobo.models.articles
import dev.vaidilya.zobo.models.detailedArticle
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface BlogsAPI {

    @GET("articles")
    suspend fun getArticles(
    ): Response<articles>

    @GET("articles/{id}")
    suspend fun getFullArticle(
        @Path("id") id: Int
    ): Response<detailedArticle>

}