package com.example.jetpackcomposeapp.graphqlpractice.data

import com.apollographql.apollo3.exception.ApolloException
import com.example.jetpackcomposeapp.GetUserPostsQuery
import com.example.jetpackcomposeapp.GetUserQuery
import com.example.jetpackcomposeapp.graphqlpractice.apollo.ApolloClientProvider
import com.example.jetpackcomposeapp.graphqlpractice.model.PostData
import com.example.jetpackcomposeapp.graphqlpractice.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository {
    fun getUser(userId: String): Flow<UserData?> = flow {
        try {
            val response = ApolloClientProvider.apolloClient
                .query(GetUserQuery(userId))
                .execute()

            response.data?.user?.let { gqlUser ->
                emit(
                    UserData(
                        id = gqlUser.id,
                        name = gqlUser.name,
                        email = gqlUser.email,
                        phone = gqlUser.phone,
                        website = gqlUser.website
                    )
                )
            }
        } catch (e: ApolloException) {
            e.printStackTrace()
            emit(null)
        }
    }

    fun getUserPosts(userId: String): Flow<List<PostData>> = flow {
        try {
            val response = ApolloClientProvider.apolloClient
                .query(GetUserPostsQuery(userId))
                .execute()

            val posts = response.data?.user?.posts?.data?.map {
                PostData(
                    id = it?.id,
                    title = it?.title,
                    body = it?.body
                )
            } ?: emptyList()

            emit(posts)
        } catch (e: ApolloException) {
            e.printStackTrace()
            emit(emptyList())
        }
    }
}
