package com.favqsclient.kmm.domain.entity

data class GetUserResultData(
    val login: String,
    val pictureUrl: String,
    val publicFavoritesCount: Long,
    val followers: Long,
    val following: Long,
    val pro: Boolean,
    val accountDetails: AccountDetails?
) : ResultData {

    data class AccountDetails(
        val email: String,
        val privateFavoritesCount: Long,
        val activeThemeId: Int? = null,
        val proExpiration: String? = null
    )
}