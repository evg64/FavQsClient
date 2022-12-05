package com.favqsclient.kmm.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

private const val BASE_URL = "https://favqs.com/api"
private const val GET_TEST_PAGE = "$BASE_URL/qotd"

class RepositoryImpl: Repository {

    override fun createUser(login: String, email: String, password: String) {

    }

    override suspend fun getTestPage(): String {
        val client = HttpClient()
        val response: HttpResponse = client.get(GET_TEST_PAGE)
        return response.body()
    }
}