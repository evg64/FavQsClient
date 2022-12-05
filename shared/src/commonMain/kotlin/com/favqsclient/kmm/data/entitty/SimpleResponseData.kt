package com.favqsclient.kmm.data.entitty

import kotlinx.serialization.Serializable

@Serializable
data class SimpleResponseData(
    val message: String
) : ApiResponseData