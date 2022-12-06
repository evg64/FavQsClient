package com.favqsclient.kmm.domain

import com.favqsclient.kmm.data.Repository
import com.favqsclient.kmm.data.RepositoryImpl
import com.favqsclient.kmm.data.request.QuoteType
import com.favqsclient.kmm.data.response.ApiResponse
import com.favqsclient.kmm.data.response.ApiResponseData
import com.favqsclient.kmm.data.response.CreateSessionResponseData
import com.favqsclient.kmm.data.response.ListQuotesResponseData
import com.favqsclient.kmm.data.response.QuoteResponseData
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
import com.favqsclient.kmm.domain.entity.ResultInputLoginArguments
import com.favqsclient.kmm.domain.entity.ResultInvalidArguments
import com.favqsclient.kmm.domain.entity.ResultSuccess
import com.favqsclient.kmm.domain.entity.SimpleResultData

object InteractorImpl : Interactor {
    private val repository: Repository = RepositoryImpl()

    private fun <T : ApiResponseData, S : ResultData> checkErrors(result: ApiResponse<T>?): ResultError<S>? {
        if (result == null) return ResultException(RuntimeException("Repository is null"))
        if (result.errorData != null) {
            return ResultApiError(result.errorData.code, result.errorData.message)
        }
        return null
    }

    override suspend fun createSession(login: String, password: String): Result<CreateSessionResultData> {
        if (login.isEmpty() && password.isEmpty()) {
            return ResultInputLoginArguments()
        } else if (login.isEmpty()) {
            return ResultInvalidArguments(listOf(InvalidField("email", "Введите данные для входа")))
        } else if (password.isEmpty()) {
            return ResultInvalidArguments(listOf(InvalidField("password", "Введите данные для входа")))
        }

        val result = repository.createSession(login, password)

        checkErrors<CreateSessionResponseData, CreateSessionResultData>(result)?.let {
            return it
        }

        result.data?.let {
            return ResultSuccess(
                CreateSessionResultData(
                    login = it.login,
                    email = it.email
                )
            )
        }

        return ResultException(RuntimeException("Unknown error"))
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

    /**
     * A list of quotes, paged 25 at a time.
     * Optional parameters:
     * @param page    Page number (25 quotes per page), default 1
     * @param filter    Type lookup or keyword search
     * @param type    ['author', 'tag', 'user']
     * @param private Get private quotes for the pro user session (e.g., private=true), default false
     * @param hidden    Get hidden quotes for the user session (e.g., hidden=true), default false
     */
    override suspend fun listQuotes(
        page: Long,
        filter: String?,
        type: QuoteType?,
        private: Boolean,
        hidden: Boolean): Result<ListQuotesResultData> {

        val result = repository.listQuotes(page, filter, type, private, hidden)

        checkErrors<ListQuotesResponseData, ListQuotesResultData>(result)?.let {
            return it
        }

        result.data?.let {
            return ResultSuccess(
                ListQuotesResultData(
                    page = it.page,
                    lastPage = it.lastPage,
                    quotes = it.quotes.map { quote ->
                        QuoteResultData(
                            tags = quote.tags,
                            favorite = quote.favorite,
                            authorPermalink = quote.authorPermalink,
                            body = quote.body,
                            id = quote.id,
                        favoritesCount = quote.favoritesCount,
                        upVotesCount = quote.upVotesCount,
                        downVotesCount = quote.downVotesCount,
                        dialogue = quote.dialogue,
                        author = quote.author,
                        url = quote.url
                    ) }
                )
            )
        }

        return ResultException(RuntimeException("Unknown error"))
    }

    override suspend fun getQuote(id: Long): Result<QuoteResultData> {
        val result = repository.getQuote(id)
        checkErrors<QuoteResponseData, QuoteResultData>(result)?.let {
            return it
        }

        result.data?.let {
            return ResultSuccess(
                QuoteResultData(
                    tags = it.tags,
                    favorite = it.favorite,
                    authorPermalink = it.authorPermalink,
                    body = it.body,
                    id = it.id,
                    favoritesCount = it.favoritesCount,
                    upVotesCount = it.upVotesCount,
                    downVotesCount = it.downVotesCount,
                    dialogue = it.dialogue,
                    author = it.author,
                    url = it.url
                )
            )
        }

        return ResultException(RuntimeException("Unknown error"))
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