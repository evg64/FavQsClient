package com.favqsclient.kmm.data

import com.favqsclient.kmm.data.entitty.ErrorResponseData

class ResponseException(val data: ErrorResponseData) : Exception()