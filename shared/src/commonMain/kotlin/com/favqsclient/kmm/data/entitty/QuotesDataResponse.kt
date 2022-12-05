package com.favqsclient.kmm.data.entitty
import kotlinx.serialization.Serializable

@Serializable
data class QuoteResponseData(
    val tags: List<String>,
    val favorite: Boolean,
    val authorPermalink: String,
    val body: String,
    val id: Long,
    val favoritesCount: Long,
    val upVotesCount: Long,
    val downVotesCount: Long,
    val dialogue: Boolean,
    val author: String,
    val url: String
): ApiResponseData

@Serializable
data class ListQuotesResponseData(
    val page: Int,
    val lastPage: Boolean,
    val quotes: List<QuoteResponseData>
) : ApiResponseData

@Serializable
data class FavQuotesResponseData(
    val favorite: Boolean,
    val quote: QuoteResponseData
) : ApiResponseData

