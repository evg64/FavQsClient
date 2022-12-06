package com.favqsclient.kmm.data

import com.favqsclient.kmm.data.request.QuoteType
import com.favqsclient.kmm.data.response.ApiResponse
import com.favqsclient.kmm.data.response.CreateSessionResponseData
import com.favqsclient.kmm.data.response.CreateUserResponseData
import com.favqsclient.kmm.data.response.FavQuotesResponseData
import com.favqsclient.kmm.data.response.GetUserResponseData
import com.favqsclient.kmm.data.response.ListQuotesResponseData
import com.favqsclient.kmm.data.response.QuoteResponseData
import com.favqsclient.kmm.data.response.SimpleResponseData

interface Repository {
    suspend fun createSession(login: String, password: String): ApiResponse<CreateSessionResponseData>
    suspend fun destroySession(): ApiResponse<SimpleResponseData>

    suspend fun createUser(login: String, email: String, password: String): ApiResponse<CreateUserResponseData>
    suspend fun getUser(login: String): ApiResponse<GetUserResponseData>
    suspend fun updateUser(login: String): ApiResponse<SimpleResponseData>

    suspend fun forgotPassword(email: String): ApiResponse<SimpleResponseData>

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
        hidden: Boolean = false
    ): ApiResponse<ListQuotesResponseData>

    suspend fun getQuote(id: Long): ApiResponse<QuoteResponseData>
    suspend fun favQuote(id: Long, fav: Boolean): ApiResponse<FavQuotesResponseData>
}