package com.favqsclient.kmm.domain.entity

sealed interface Result<T: ResultData>

class ResultSuccess<T : ResultData>(val data: T) : Result<T>
sealed interface ResultError<T : ResultData>: Result<T>

class ResultInvalidArguments<T : ResultData>(val invalidFields: List<InvalidField>) : ResultError<T>
class ResultInputLoginArguments<T : ResultData> : ResultError<T>
class ResultException<T : ResultData>(val e: Throwable) : ResultError<T>
class ResultApiError<T : ResultData>(val code: Int, val message: String?) : ResultError<T>

sealed interface ResultData

data class InvalidField(val field: String, val message: String)