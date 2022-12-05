package com.favqsclient.kmm.domain.entity

data class ErrorResponse(
    private val isServerError: Boolean,
    private val code: Int,
    private val message: String
) : ResultData