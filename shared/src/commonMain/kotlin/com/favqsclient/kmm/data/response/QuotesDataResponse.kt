package com.favqsclient.kmm.data.response
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteResponseData(
    @SerialName("tags")
    val tags: List<String> = emptyList(),
    @SerialName("favorite")
    val favorite: Boolean = false,
    @SerialName("author_permalink")
    val authorPermalink: String = "",
    @SerialName("body")
    val body: String = "",
    @SerialName("id")
    val id: Long = 0,
    @SerialName("favorites_count")
    val favoritesCount: Long = 0,
    @SerialName("upvotes_count")
    val upVotesCount: Long = 0,
    @SerialName("downvotes_count")
    val downVotesCount: Long = 0,
    @SerialName("dialogue")
    val dialogue: Boolean = false,
    @SerialName("author")
    val author: String = "",
    @SerialName("url")
    val url: String = ""
): ApiResponseData

@Serializable
data class ListQuotesResponseData(
    @SerialName("page")
    val page: Int = 1,
    @SerialName("last_page")
    val lastPage: Boolean = false,
    @SerialName("quotes")
    val quotes: List<QuoteResponseData> = emptyList()
) : ApiResponseData

@Serializable
data class FavQuotesResponseData(
    val favorite: Boolean,
    val quote: QuoteResponseData
) : ApiResponseData

