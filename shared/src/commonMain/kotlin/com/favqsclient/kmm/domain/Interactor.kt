package com.favqsclient.kmm.domain

import com.favqsclient.kmm.domain.entity.CreateSessionResultData
import com.favqsclient.kmm.domain.entity.CreateUserResultData
import com.favqsclient.kmm.domain.entity.FavQuotesResultData
import com.favqsclient.kmm.domain.entity.GetUserResultData
import com.favqsclient.kmm.domain.entity.ListQuotesResultData
import com.favqsclient.kmm.domain.entity.QuoteResultData
import com.favqsclient.kmm.domain.entity.Result
import com.favqsclient.kmm.domain.entity.SimpleResultData

interface Interactor {
    suspend fun createSession(login: String, password: String): Result<CreateSessionResultData>
    suspend fun destroySession(): Result<SimpleResultData>

    suspend fun createUser(login: String, email: String, password: String): Result<CreateUserResultData>
    suspend fun getUser(login: String): Result<GetUserResultData>
    suspend fun updateUser(login: String): Result<SimpleResultData>

    suspend fun forgotPassword(email: String): Result<SimpleResultData>

    suspend fun listQuotes(): Result<ListQuotesResultData>
    suspend fun getQuote(id: Long): Result<QuoteResultData>
    suspend fun favQuote(id: Long, fav: Boolean): Result<FavQuotesResultData>
}