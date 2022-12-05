package com.favqsclient.kmm.data.entitty

sealed interface ApiResponse<T: ApiResponseData>

class ApiSuccess<T : ApiResponseData>(val data: T) : ApiResponse<T>
class ApiError<T : ApiResponseData>(val code: Int, val message: String?) : ApiResponse<T>
class ApiException<T : ApiResponseData>(val e: Throwable) : ApiResponse<T>

interface ApiResponseData