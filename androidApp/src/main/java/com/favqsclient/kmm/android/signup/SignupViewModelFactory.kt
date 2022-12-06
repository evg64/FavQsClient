package com.favqsclient.kmm.android.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.favqsclient.kmm.domain.InteractorImpl

class SignupViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignupViewModel(interactor = InteractorImpl) as T
    }
}