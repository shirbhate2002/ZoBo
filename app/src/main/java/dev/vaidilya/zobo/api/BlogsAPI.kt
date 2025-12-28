package dev.vaidilya.zobo.api

import dev.vaidilya.zobo.models.GraphQLRequest
import dev.vaidilya.zobo.models.articlesList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface BlogsAPI {

@POST("api/fetch")
suspend fun getArticles(
    @Body request: GraphQLRequest
): Response<articlesList>

}