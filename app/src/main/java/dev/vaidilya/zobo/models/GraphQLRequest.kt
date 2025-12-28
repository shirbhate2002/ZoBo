package dev.vaidilya.zobo.models

data class GraphQLRequest(
    val query: String,
    val variables: Map<String, Any?>
)