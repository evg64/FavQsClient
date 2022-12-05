package com.favqsclient.kmm.android.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *
 *
 * @author Evgeny Chumak
 **/
class LoginViewModelFactory(
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val interactor = com.favqsclient.kmm.domain.InteractorImpl
        return LoginViewModel(interactor = interactor) as T
    }
}