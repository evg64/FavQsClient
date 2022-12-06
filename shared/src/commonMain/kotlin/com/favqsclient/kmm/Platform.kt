package com.favqsclient.kmm

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient