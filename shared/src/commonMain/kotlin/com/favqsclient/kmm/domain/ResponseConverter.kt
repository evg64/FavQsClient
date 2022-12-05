package com.favqsclient.kmm.domain

import com.favqsclient.kmm.data.entitty.ApiError
import com.favqsclient.kmm.data.entitty.ApiException
import com.favqsclient.kmm.data.entitty.ApiDataResponse
import com.favqsclient.kmm.data.entitty.ApiResult
import com.favqsclient.kmm.data.entitty.ApiSuccess
import com.favqsclient.kmm.data.entitty.CreateSessionDataResponse
import com.favqsclient.kmm.domain.entity.CreateSessionResultData
import com.favqsclient.kmm.domain.entity.ResultData
import com.favqsclient.kmm.domain.entity.ResultError
import com.favqsclient.kmm.domain.entity.ResultException
import com.favqsclient.kmm.domain.entity.ResultSuccess

class ResponseConverter {

    // inline fun <reified T : ApiDataResponse, reified S: ResultData> convert(data: ApiResult<T>): Response<S> =
    //     when (data) {
    //         is ApiException -> ResultException(data.e)
    //         is ApiError -> ResultError(data.code, data.message)
    //         is ApiSuccess -> convertData(data.data)
    //     }
    //
    // inline fun <reified T : ApiDataResponse, reified S: ResultData> convertData(data: T): ResultSuccess<S> =
    //     when(data) {
    //         is CreateSessionDataResponse -> CreateSessionResultData()
    //         else ->
    //     }
}