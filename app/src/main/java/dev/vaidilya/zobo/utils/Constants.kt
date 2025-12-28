package dev.vaidilya.zobo.utils


object Constants {

    const val BASE_URL = "https://blog.zomato.com/"
    const val X_API_KEY = "4e754626-be95-11ed-a9d6-15f5bdc28dc"

    const val POSTS_QUERY = """
        query GetPosts(
          ${'$'}categoryIds: [ID]
          ${'$'}endCursor: String
          ${'$'}count: Int!
        ) {
          posts(
            first: ${'$'}count
            after: ${'$'}endCursor
            where: { categoryIn: ${'$'}categoryIds }
          ) {
            nodes {
              date
              title
              id
              uri
              featuredImage {
                node {
                  mediaItemUrl
                }
              }
            }
            pageInfo {
              endCursor
              hasNextPage
            }
          }
        }
        """

}