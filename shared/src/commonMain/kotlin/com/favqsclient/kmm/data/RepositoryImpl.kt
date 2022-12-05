package com.favqsclient.kmm.data

import com.favqsclient.kmm.data.entitty.ApiResponse
import com.favqsclient.kmm.data.entitty.CreateSessionResponseData
import com.favqsclient.kmm.data.entitty.CreateUserResponseData
import com.favqsclient.kmm.data.entitty.FavQuotesResponseData
import com.favqsclient.kmm.data.entitty.GetUserResponseData
import com.favqsclient.kmm.data.entitty.ListQuotesResponseData
import com.favqsclient.kmm.data.entitty.QuoteResponseData
import com.favqsclient.kmm.data.entitty.SimpleResponseData
import io.ktor.client.HttpClient

private const val BASE_URL = "https://favqs.com/api"
private const val CREATE_SESSION_URL = "$BASE_URL/qotd"
private const val DESTROY_SESSION_URL = "$BASE_URL/qotd"
private const val CREATE_USER_URL = "$BASE_URL/qotd"
private const val GET_USER_URL = "$BASE_URL/qotd"
private const val UPDATE_USER_URL = "$BASE_URL/qotd"
private const val FORGOT_PASSWORD_URL = "$BASE_URL/qotd"
private const val LIST_QUOTES_URL = "$BASE_URL/qotd"
private const val GET_QUOTES_URL = "$BASE_URL/qotd"
private const val FAV_QUOTES_URL = "$BASE_URL/qotd"
private const val UNFAV_QUOTES_URL = "$BASE_URL/qotd"

class RepositoryImpl : Repository {
    val client = defaultHttpClient()
    var token: String? = null

    override suspend fun createSession(login: String, password: String): ApiResponse<CreateSessionResponseData> {
        TODO("Not yet implemented")
    }

    override suspend fun destroySession(): ApiResponse<SimpleResponseData> {
        TODO("Not yet implemented")
    }

    override suspend fun createUser(
        login: String,
        email: String,
        password: String
    ): ApiResponse<CreateUserResponseData> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(login: String): ApiResponse<GetUserResponseData> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(login: String): ApiResponse<SimpleResponseData> {
        TODO("Not yet implemented")
    }

    override suspend fun forgotPassword(email: String): ApiResponse<SimpleResponseData> {
        TODO("Not yet implemented")
    }

    override suspend fun listQuotes(): ApiResponse<ListQuotesResponseData> {
        TODO("Not yet implemented")
    }

    override suspend fun getQuote(id: Long): ApiResponse<QuoteResponseData> {
        TODO("Not yet implemented")
    }

    override suspend fun favQuote(id: Long, fav: Boolean): ApiResponse<FavQuotesResponseData> {
        TODO("Not yet implemented")
    }

    companion object {
        fun defaultHttpClient() = HttpClient()
    }
}