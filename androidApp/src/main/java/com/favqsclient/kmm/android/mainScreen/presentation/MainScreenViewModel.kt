package com.favqsclient.kmm.android.mainScreen.presentation

import androidx.lifecycle.ViewModel
import com.favqsclient.kmm.android.mainScreen.model.Quote
import com.favqsclient.kmm.domain.Interactor

/**
 *
 *
 * @author Sergey Ozerny
 **/

class MainScreenViewModel(
    private val interactor: Interactor,
) : ViewModel() {
    fun getQuotes(): MutableList<Quote> {
        return MutableList(10){ Quote.getMockQuote() }
    }
}