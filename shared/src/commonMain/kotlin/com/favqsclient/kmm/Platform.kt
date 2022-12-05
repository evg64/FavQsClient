package com.favqsclient.kmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform