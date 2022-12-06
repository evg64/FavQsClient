package com.favqsclient.kmm.android.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.favqsclient.kmm.domain.InteractorImpl

/**
 *
 *
 * @author Evgeny Chumak
 **/
class LoginViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(interactor = InteractorImpl) as T
    }
}