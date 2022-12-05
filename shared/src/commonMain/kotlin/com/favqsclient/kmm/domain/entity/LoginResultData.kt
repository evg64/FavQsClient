package com.favqsclient.kmm.domain.entity

/**
 *
 *
 * @author Evgeny Chumak
 **/
data class LoginResultData(
    private val user: String,
    private val login: String,
) : ResultData