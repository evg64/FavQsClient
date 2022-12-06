package com.favqsclient.kmm.android.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.favqsclient.kmm.data.RepositoryImpl
import com.favqsclient.kmm.domain.InteractorImpl

/**
 *
 *
 * @author Evgeny Chumak
 **/
class LoginViewModelFactory(
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = RepositoryImpl()
        val interactor = InteractorImpl.invoke(repository)
        return LoginViewModel(interactor = interactor) as T
    }
}