package com.favqsclient.kmm.data.response

import kotlinx.serialization.Serializable

@Serializable
data class SimpleResponseData(
    val message: String
) : ApiResponseData