package com.favqsclient.kmm.android.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.favqsclient.kmm.data.RepositoryImpl
import com.favqsclient.kmm.login.domain.LoginInteractor

/**
 *
 *
 * @author Evgeny Chumak
 **/
class LoginViewModelFactory(
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val interactor = LoginInteractor(RepositoryImpl())
        return LoginViewModel(interactor = interactor) as T
    }
}