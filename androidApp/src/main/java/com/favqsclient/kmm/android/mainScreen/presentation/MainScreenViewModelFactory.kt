package com.favqsclient.kmm.android.mainScreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.favqsclient.kmm.android.login.presentation.LoginViewModel
import com.favqsclient.kmm.data.RepositoryImpl
import com.favqsclient.kmm.domain.InteractorImpl

/**
 *
 *
 * @author Sergey Ozerny
 **/

class MainScreenViewModelFactory(
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val interactor = InteractorImpl
        return MainScreenViewModel(interactor = interactor) as T
    }
}