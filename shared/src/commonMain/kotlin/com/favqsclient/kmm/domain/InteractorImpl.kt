package com.favqsclient.kmm.domain

import com.favqsclient.kmm.data.Repository
import com.favqsclient.kmm.data.RepositoryImpl
import com.favqsclient.kmm.domain.entity.CreateSessionResultData
import com.favqsclient.kmm.domain.entity.CreateUserResultData
import com.favqsclient.kmm.domain.entity.FavQuotesResultData
import com.favqsclient.kmm.domain.entity.GetUserResultData
import com.favqsclient.kmm.domain.entity.ListQuotesResultData
import com.favqsclient.kmm.domain.entity.QuoteResultData
import com.favqsclient.kmm.domain.entity.Result
import com.favqsclient.kmm.domain.entity.ResultInvalidArguments
import com.favqsclient.kmm.domain.entity.ResultSuccess
import com.favqsclient.kmm.domain.entity.SimpleResultData
import com.favqsclient.kmm.domain.entity.InvalidField

object InteractorImpl : Interactor {
    val repository: Repository = RepositoryImpl()

    override suspend fun createSession(login: String, password: String): Result<CreateSessionResultData> {
        if(login.isEmpty()) {
            return ResultInvalidArguments(listOf(InvalidField("email", "Введите данные для входа")))
        }

        return ResultSuccess(
            CreateSessionResultData(
                login = "login",
                email = "email"
            )
        )
    }

    override suspend fun destroySession(): Result<SimpleResultData> {
        return ResultSuccess(SimpleResultData("OK"))
    }

    override suspend fun createUser(login: String, email: String, password: String): Result<CreateUserResultData> {
        return ResultSuccess(CreateUserResultData(login = "login"))
    }

    override suspend fun getUser(login: String): Result<GetUserResultData> {
        return ResultSuccess(
            GetUserResultData(
                login = "login",
                pictureUrl = "pictureUrl",
                publicFavoritesCount = 0,
                followers = 0,
                following = 0,
                pro = false,
                accountDetails = GetUserResultData.AccountDetails(
                    email = "email",
                    privateFavoritesCount = 0
                )
            )
        )
    }

    override suspend fun updateUser(login: String): Result<SimpleResultData> {
        return ResultSuccess(SimpleResultData("OK"))
    }

    override suspend fun forgotPassword(email: String): Result<SimpleResultData> {
        return ResultSuccess(SimpleResultData("OK"))
    }

    override suspend fun listQuotes(): Result<ListQuotesResultData> {
        return ResultSuccess(
            ListQuotesResultData(
                page = 1,
                lastPage = true,
                quotes = listOf(
                    QuoteResultData(
                        tags = listOf("linux", "programming", "code", "finnish-american"),
                        favorite = false,
                        authorPermalink = "authorPermalink",
                        body = "body",
                        id = 145345,
                        favoritesCount = 0,
                        upVotesCount = 0,
                        downVotesCount = 0,
                        dialogue = false,
                        author = "author",
                        url = "url"
                    )
                )
            )
        )
    }

    override suspend fun getQuote(id: Long): Result<QuoteResultData> {
        return ResultSuccess(
            QuoteResultData(
                tags = listOf("linux", "programming", "code", "finnish-american"),
                favorite = false,
                authorPermalink = "authorPermalink",
                body = "body",
                id = 145345,
                favoritesCount = 0,
                upVotesCount = 0,
                downVotesCount = 0,
                dialogue = false,
                author = "author",
                url = "url"
            )
        )
    }

    override suspend fun favQuote(id: Long, fav: Boolean): Result<FavQuotesResultData> {
        return ResultSuccess(
            FavQuotesResultData(
                favorite = fav,
                quote = QuoteResultData(
                    tags = listOf("linux", "programming", "code", "finnish-american"),
                    favorite = false,
                    authorPermalink = "authorPermalink",
                    body = "body",
                    id = 145345,
                    favoritesCount = 0,
                    upVotesCount = 0,
                    downVotesCount = 0,
                    dialogue = false,
                    author = "author",
                    url = "url"
                )
            )
        )
    }
}