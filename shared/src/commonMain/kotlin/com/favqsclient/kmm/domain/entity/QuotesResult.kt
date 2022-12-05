package com.favqsclient.kmm.domain.entity

data class QuoteResultData(
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
): ResultData

data class ListQuotesResultData(
    val page: Int,
    val lastPage: Boolean,
    val quotes: List<QuoteResultData>
) : ResultData

data class FavQuotesResultData(
    val favorite: Boolean,
    val quote: QuoteResultData
) : ResultData

