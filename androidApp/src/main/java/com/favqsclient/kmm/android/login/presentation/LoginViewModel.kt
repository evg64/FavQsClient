package com.favqsclient.kmm.android.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.favqsclient.kmm.domain.Interactor
import com.favqsclient.kmm.domain.entity.ResultApiError
import com.favqsclient.kmm.domain.entity.ResultException
import com.favqsclient.kmm.domain.entity.ResultInvalidArguments
import com.favqsclient.kmm.domain.entity.ResultSuccess
import kotlinx.coroutines.launch

/**
 *
 *
 * @author Evgeny Chumak
 **/
class LoginViewModel(
    private val interactor: Interactor,
) : ViewModel() {


    private val _login = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    val login: LiveData<String> = _login
    val password: LiveData<String> = _password

    fun onLoginClick() {
        println("onLoginClick")
        viewModelScope.launch {
            when (val result = interactor.createSession(login.value ?: "", password.value ?: "")) {
                is ResultSuccess -> {
                    println("login: " + result.data.login)
                }
                is ResultInvalidArguments -> {
                    println(result.invalidFields[0].message)
                }
                is ResultException -> {
                    println(result.e)
                }
                is ResultApiError -> {
                    println("Api error [${result.code}] ${result.message}")
                }
            }
        }
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