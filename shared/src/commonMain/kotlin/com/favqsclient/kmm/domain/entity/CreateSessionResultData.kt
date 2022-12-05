package com.favqsclient.kmm.domain.entity

data class CreateSessionResultData(
    val login: String,
    val email: String
) : ResultData