package com.example.jetpackcomposeapp.graphqlpractice.apollo

import com.apollographql.apollo3.ApolloClient

object ApolloClientProvider {
    val apolloClient: ApolloClient = ApolloClient.Builder()
        .serverUrl("https://graphqlzero.almansi.me/api")
        .build()
}