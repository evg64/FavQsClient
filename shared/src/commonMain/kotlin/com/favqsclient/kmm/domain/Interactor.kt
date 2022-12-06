package com.favqsclient.kmm.domain

import com.favqsclient.kmm.data.request.QuoteType
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

    /**
     * A list of quotes, paged 25 at a time.
     * Optional parameters:
     * @param page    Page number (25 quotes per page), default 1
     * @param filter    Type lookup or keyword search
     * @param type    ['author', 'tag', 'user']
     * @param private Get private quotes for the pro user session (e.g., private=true), default false
     * @param hidden    Get hidden quotes for the user session (e.g., hidden=true), default false
     */
    suspend fun listQuotes(
        page: Long = 1,
        filter: String?,
        type: QuoteType?,
        private: Boolean = false,
        hidden: Boolean = false): Result<ListQuotesResultData>
    suspend fun getQuote(id: Long): Result<QuoteResultData>
    suspend fun favQuote(id: Long, fav: Boolean): Result<FavQuotesResultData>
}