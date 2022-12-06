package com.favqsclient.kmm.android.mainScreen.model

data class Quote(
    val id: Int,
    val dialogue: Boolean,
    val private: Boolean,
    val tags: List<String>,
    val url: String,
    val favoritesCount: Int,
    val upvotesCount: Int,
    val downvotesCount: Int,
    val author: String,
    val authorPermalink: String,
    val body: String
){
    companion object {
        fun getMockQuote() = Quote(
            id = 595,
            dialogue = false,
            private = false,
            tags = listOf("belief", "nature", "ability"),
            url = "https://favqs.com/quotes/anonymous/595-a-bird-perched--",
            favoritesCount = 4,
            upvotesCount = 0,
            downvotesCount = 0,
            author = "Anonymous",
            authorPermalink = "anonymous",
            body = "A bird perched on a branch is never afraid of the branch breaking.  Her trust is in her wings."
        )
    }
}