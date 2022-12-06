package com.favqsclient.kmm.android.mainScreen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.favqsclient.kmm.android.mainScreen.model.Quote
import com.favqsclient.kmm.domain.Interactor
import com.favqsclient.kmm.domain.entity.ResultApiError
import com.favqsclient.kmm.domain.entity.ResultException
import com.favqsclient.kmm.domain.entity.ResultInputLoginArguments
import com.favqsclient.kmm.domain.entity.ResultInvalidArguments
import com.favqsclient.kmm.domain.entity.ResultSuccess
import kotlinx.coroutines.launch

/**
 *
 *
 * @author Sergey Ozerny
 **/

class MainScreenViewModel(
    private val interactor: Interactor,
) : ViewModel() {
    private val _quotes = MutableLiveData<List<Quote>>()
    val quotes: LiveData<List<Quote>> = _quotes

    init {
        getQuotes()
    }

    private fun getQuotes() {
        viewModelScope.launch {
            val quotesResult = interactor.listQuotes(
                filter = null,
                type = null,
            )
            when (quotesResult) {
                is ResultSuccess -> {
                    _quotes.value = quotesResult.data.quotes.map { src ->
                        Quote(
                            id = src.id.toInt(),
                            dialogue = src.dialogue,
                            private = false,
                            tags = src.tags,
                            url = src.url,
                            favoritesCount = src.favoritesCount.toInt(),
                            upvotesCount = src.upVotesCount.toInt(),
                            downvotesCount = src.downVotesCount.toInt(),
                            author = src.author,
                            authorPermalink = src.authorPermalink,
                            body = src.body,
                        )
                    }
                }
                is ResultInvalidArguments -> {
                    // not supported
                }
                is ResultInputLoginArguments -> {
                    // not supported
                }
                is ResultException -> {
                    // not supported
                }
                is ResultApiError -> {
                    // not supported
                }
            }
        }
    }
}