package com.favqsclient.kmm.android.mainScreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.favqsclient.kmm.domain.InteractorImpl

/**
 *
 *
 * @author Sergey Ozerny
 **/

class MainScreenViewModelFactory(
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainScreenViewModel(interactor = InteractorImpl) as T
    }
}