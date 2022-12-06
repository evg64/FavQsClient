package com.favqsclient.kmm.data

import com.favqsclient.kmm.data.request.ApiRequest
import com.favqsclient.kmm.data.request.CreateSessionRequest
import com.favqsclient.kmm.data.request.CreateUserRequest
import com.favqsclient.kmm.data.request.QuoteType
import com.favqsclient.kmm.data.response.ApiResponse
import com.favqsclient.kmm.data.response.ApiResponseData
import com.favqsclient.kmm.data.response.CreateSessionResponseData
import com.favqsclient.kmm.data.response.CreateUserResponseData
import com.favqsclient.kmm.data.response.ErrorResponseData
import com.favqsclient.kmm.data.response.FavQuotesResponseData
import com.favqsclient.kmm.data.response.GetUserResponseData
import com.favqsclient.kmm.data.response.ListQuotesResponseData
import com.favqsclient.kmm.data.response.QuoteResponseData
import com.favqsclient.kmm.data.response.SimpleResponseData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.URLBuilder
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.appendIfNameAbsent
import kotlinx.serialization.json.Json

private const val APP_TOKEN = "1c50b74572c520e00fae5e4f8b4c19c1"

private const val BASE_URL = "https://favqs.com/api"
private const val SESSION_URL = "$BASE_URL/session"
private const val USER_URL = "$BASE_URL/users"
private const val FORGOT_PASSWORD_URL = "$USER_URL/forgot_password"
private const val QUOTES_URL = "$BASE_URL/quotes"

class RepositoryImpl : Repository {
    var client: HttpClient = defaultHttpClient()
    var token: String = ""

    private suspend inline fun <reified T : ApiResponseData> handleApi(
        method: HttpMethod, url: String, bodyRequest: ApiRequest? = null
    ): ApiResponse<T> {
        val block: HttpRequestBuilder.() -> Unit = {
            this.method = method
            contentType(ContentType.Application.Json)
            headers {
                appendIfNameAbsent("Authorization", "Token token=\"$APP_TOKEN\"")
                if (token.isNotEmpty()) {
                    appendIfNameAbsent("User-Token", token)
                }
            }
            bodyRequest?.let {
                setBody(it)
            }
        }
        val response: HttpResponse = client.request(url, block)

        when (response.status.value) {
            200 -> {
                return try {
                    val data: T = response.body()
                    ApiResponse(data)
                } catch (e: Exception) {
                    val error: ErrorResponseData = response.body()
                    ApiResponse(errorData = error)
                }
            }
            in 300..399 -> println(response.bodyAsText())
            in 400..499 -> println(response.bodyAsText())
            in 500..599 -> println(response.bodyAsText())
        }
        return ApiResponse()
    }

    override suspend fun createSession(login: String, password: String): ApiResponse<CreateSessionResponseData> {
        val response = handleApi<CreateSessionResponseData>(
            HttpMethod.Post, SESSION_URL, CreateSessionRequest(
                CreateSessionRequest.CreateSessionUserData(login, password)
            )
        )
        response.data?.let {
            token = response.data.token
        }
        return response
    }

    override suspend fun destroySession(): ApiResponse<SimpleResponseData> {
        val response = handleApi<SimpleResponseData>(
            HttpMethod.Delete, SESSION_URL
        )
        response.data?.let {
            token = ""
        }
        return response
    }

    override suspend fun createUser(
        login: String,
        email: String,
        password: String
    ): ApiResponse<CreateUserResponseData> {
        val response = handleApi<CreateUserResponseData>(
            HttpMethod.Post, USER_URL, CreateUserRequest(login, email, password)
        )
        response.data?.let {
            token = response.data.token
        }
        return response
    }

    override suspend fun getUser(login: String): ApiResponse<GetUserResponseData> =
        handleApi(HttpMethod.Get, "${USER_URL}/:$login")

    override suspend fun updateUser(login: String): ApiResponse<SimpleResponseData> {
        TODO("Not yet implemented")
    }

    override suspend fun forgotPassword(email: String): ApiResponse<SimpleResponseData> {
        TODO("Not yet implemented")
    }

    override suspend fun listQuotes(
        page: Long,
        filter: String?,
        type: QuoteType?,
        private: Boolean,
        hidden: Boolean
    ): ApiResponse<ListQuotesResponseData> {
        val uri = URLBuilder(QUOTES_URL)
        uri.parameters.append("page", page.toString())
        filter?.let {
            uri.parameters.append("filter", filter)
        }
        type?.let {
            uri.parameters.append("type", type.name)
        }
        uri.parameters.append("private", private.toString())
        uri.parameters.append("hidden", hidden.toString())
        return handleApi(HttpMethod.Get, uri.buildString())
    }

    override suspend fun getQuote(id: Long): ApiResponse<QuoteResponseData> =
        handleApi(HttpMethod.Get, "${QUOTES_URL}/:$id")

    override suspend fun favQuote(id: Long, fav: Boolean): ApiResponse<FavQuotesResponseData> {
        TODO("Not yet implemented")
    }

    companion object {
        fun defaultHttpClient() = HttpClient {
            install(HttpCache)
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }
}