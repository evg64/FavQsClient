package com.favqsclient.kmm.data

import com.favqsclient.kmm.data.response.ErrorResponseData

class ResponseException(val data: ErrorResponseData) : Exception()