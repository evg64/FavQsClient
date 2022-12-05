package com.favqsclient.kmm.android.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.favqsclient.kmm.login.domain.LoginInteractor

/**
 *
 *
 * @author Evgeny Chumak
 **/
class LoginViewModel(
    private val interactor: LoginInteractor,
) : ViewModel() {


    private val _login = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    val login: LiveData<String> = _login
    val password: LiveData<String> = _password

    fun onLoginClick() {

    }

    fun onLoginChanged(newLogin: String) {
        _login.value = newLogin
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }

    fun onRegisterClick() {
    }

    fun onForgetPassword() {
    }
}