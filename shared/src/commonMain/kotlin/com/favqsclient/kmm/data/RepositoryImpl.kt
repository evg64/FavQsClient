package com.favqsclient.kmm.data

import com.favqsclient.kmm.data.entitty.ApiException
import com.favqsclient.kmm.data.entitty.ApiRequest
import com.favqsclient.kmm.data.entitty.ApiResponse
import com.favqsclient.kmm.data.entitty.ApiResponseData
import com.favqsclient.kmm.data.entitty.ApiSuccess
import com.favqsclient.kmm.data.entitty.CreateSessionRequest
import com.favqsclient.kmm.data.entitty.CreateSessionResponseData
import com.favqsclient.kmm.data.entitty.CreateUserResponseData
import com.favqsclient.kmm.data.entitty.FavQuotesResponseData
import com.favqsclient.kmm.data.entitty.GetUserResponseData
import com.favqsclient.kmm.data.entitty.ListQuotesResponseData
import com.favqsclient.kmm.data.entitty.QuoteResponseData
import com.favqsclient.kmm.data.entitty.SimpleResponseData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

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

private const val USER_TOKEN_HEADER = "User-Token"

class RepositoryImpl : Repository {
    var client: HttpClient = defaultHttpClient()
    var token: String = ""

    private suspend inline fun <reified T : ApiResponseData> handleApiPost(
        url: String, bodyRequest: ApiRequest? = null
    ): ApiResponse<T> {
        println("handleApiPost")
        val response: HttpResponse = client.post(url) {
            contentType(ContentType.Application.Json)
            bodyRequest?.let {
                setBody(it)
            }
        }
        println("status " + response.status)
        when (response.status.value) {
            200 -> {
                val data: T = response.body()
                println("success response $data")
                return ApiSuccess(data)
            }
            in 300..399 -> println(response.bodyAsText())
            in 400..499 -> println(response.bodyAsText())
            in 500..599 -> println(response.bodyAsText())
        }
        return ApiException(RuntimeException(response.bodyAsText()))
    }

    override suspend fun createSession(login: String, password: String): ApiResponse<CreateSessionResponseData> =
        handleApiPost(CREATE_SESSION_URL, CreateSessionRequest(
            CreateSessionRequest.CreateSessionUserData(login, password)
        ))

    override suspend fun destroySession(): ApiResponse<SimpleResponseData> {
        TODO("Not yet implemented")
    }

    override suspend fun createUser(
        login: String,
        email: String,
        password: String
    ): ApiResponse<CreateUserResponseData> {
        val response: CreateUserResponseData = client.get(CREATE_USER_URL) {
            if (token.isNotEmpty()) {
                headers {
                    append(USER_TOKEN_HEADER, token)
                }
            }
        }.body()
        return ApiSuccess(response)
    }

    override suspend fun getUser(login: String): ApiResponse<GetUserResponseData> {
        client.get(GET_USER_URL) {
            if (token.isNotEmpty()) {
                headers {
                    append(USER_TOKEN_HEADER, token)
                }
            }
        }
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
        fun defaultHttpClient() = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }
}