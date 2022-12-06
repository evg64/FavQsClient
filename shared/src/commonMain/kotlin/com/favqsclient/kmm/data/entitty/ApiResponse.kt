package com.favqsclient.kmm.data.entitty

sealed interface ApiResponse2<T: ApiResponseData>

// class ApiSuccess<T : ApiResponseData>(val data: T) : ApiResponse<T>
// class ApiError(val data: ErrorResponseData) : ApiResponse<ErrorResponseData>
// class ApiException<T : ApiResponseData>(val e: Throwable) : ApiResponse<T>

interface ApiResponseData

data class ApiResponse<T: ApiResponseData>(
    val data: T? = null,
    val errorData: ErrorResponseData? = null
)