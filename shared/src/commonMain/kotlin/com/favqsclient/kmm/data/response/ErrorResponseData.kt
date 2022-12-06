package com.favqsclient.kmm.data.response
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponseData(
    @SerialName("error_code")
    val code: Int,
    val message: String
) : ApiResponseData