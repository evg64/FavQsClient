package com.favqsclient.kmm.domain

import com.favqsclient.kmm.data.Repository
import com.favqsclient.kmm.data.entitty.ApiError
import com.favqsclient.kmm.data.entitty.ApiException
import com.favqsclient.kmm.data.entitty.ApiResponse
import com.favqsclient.kmm.data.entitty.ApiResponseData
import com.favqsclient.kmm.data.entitty.ApiSuccess
import com.favqsclient.kmm.data.entitty.CreateSessionResponseData
import com.favqsclient.kmm.domain.entity.CreateSessionResultData
import com.favqsclient.kmm.domain.entity.CreateUserResultData
import com.favqsclient.kmm.domain.entity.FavQuotesResultData
import com.favqsclient.kmm.domain.entity.GetUserResultData
import com.favqsclient.kmm.domain.entity.InvalidField
import com.favqsclient.kmm.domain.entity.ListQuotesResultData
import com.favqsclient.kmm.domain.entity.QuoteResultData
import com.favqsclient.kmm.domain.entity.Result
import com.favqsclient.kmm.domain.entity.ResultApiError
import com.favqsclient.kmm.domain.entity.ResultData
import com.favqsclient.kmm.domain.entity.ResultError
import com.favqsclient.kmm.domain.entity.ResultException
import com.favqsclient.kmm.domain.entity.ResultInvalidArguments
import com.favqsclient.kmm.domain.entity.ResultSuccess
import com.favqsclient.kmm.domain.entity.SimpleResultData

object InteractorImpl : Interactor {
    private data class Env(
        var repository: Repository? = null
    )

    private val env = Env()

    operator fun invoke(repository: Repository): Interactor {
        println("InteractorImpl invoke $repository")
        env.repository = repository
        println("InteractorImpl invoked ${env.repository}")
        return this
    }

    private fun <T : ApiResponseData, S : ResultData> checkErrors(result: ApiResponse<T>?): ResultError<S>? =
        when (result) {
            null -> ResultException(RuntimeException("Repository is null"))
            is ApiException -> ResultException(result.e)
            is ApiError -> ResultApiError(result.code, result.message)
            else -> null
        }

    override suspend fun createSession(login: String, password: String): Result<CreateSessionResultData> {

        println("createSession $login")

        if (login.isEmpty()) {
            return ResultInvalidArguments(listOf(InvalidField("email", "Введите данные для входа")))
        }

        val result = env.repository?.createSession(login, password)

        checkErrors<CreateSessionResponseData, CreateSessionResultData>(result)?.let {
            println("createSession error")
            return it
        }

        val data = (result as ApiSuccess).data

        return ResultSuccess(
            CreateSessionResultData(
                login = data.user,
                email = data.email
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