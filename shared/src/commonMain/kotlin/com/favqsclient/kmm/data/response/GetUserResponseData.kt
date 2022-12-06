package com.favqsclient.kmm.data.response
import kotlinx.serialization.Serializable

@Serializable
data class GetUserResponseData(
    val login: String,
    val pictureUrl: String,
    val publicFavoritesCount: Long,
    val followers: Long,
    val following: Long,
    val pro: Boolean,
    val accountDetails: AccountDetails?
) : ApiResponseData {

    @Serializable
    data class AccountDetails(
        val email: String,
        val privateFavoritesCount: Long,
        val activeThemeId: Int? = null,
        val proExpiration: String? = null
    )
}