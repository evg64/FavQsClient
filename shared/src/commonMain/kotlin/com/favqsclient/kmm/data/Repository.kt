package com.favqsclient.kmm.data

import com.favqsclient.kmm.data.entitty.ApiResponse
import com.favqsclient.kmm.data.entitty.CreateSessionResponseData
import com.favqsclient.kmm.data.entitty.CreateUserResponseData
import com.favqsclient.kmm.data.entitty.FavQuotesResponseData
import com.favqsclient.kmm.data.entitty.GetUserResponseData
import com.favqsclient.kmm.data.entitty.ListQuotesResponseData
import com.favqsclient.kmm.data.entitty.QuoteResponseData
import com.favqsclient.kmm.data.entitty.SimpleResponseData

interface Repository {
    suspend fun createSession(login: String, password: String): ApiResponse<CreateSessionResponseData>
    suspend fun destroySession(): ApiResponse<SimpleResponseData>

    suspend fun createUser(login: String, email: String, password: String): ApiResponse<CreateUserResponseData>
    suspend fun getUser(login: String): ApiResponse<GetUserResponseData>
    suspend fun updateUser(login: String): ApiResponse<SimpleResponseData>

    suspend fun forgotPassword(email: String): ApiResponse<SimpleResponseData>

    suspend fun listQuotes(): ApiResponse<ListQuotesResponseData>
    suspend fun getQuote(id: Long): ApiResponse<QuoteResponseData>
    suspend fun favQuote(id: Long, fav: Boolean): ApiResponse<FavQuotesResponseData>
}