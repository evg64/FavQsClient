package com.favqsclient.kmm.android.login.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.favqsclient.kmm.android.core.SingleLiveEvent
import com.favqsclient.kmm.domain.Interactor
import com.favqsclient.kmm.domain.entity.ResultApiError
import com.favqsclient.kmm.domain.entity.ResultException
import com.favqsclient.kmm.domain.entity.ResultInputLoginArguments
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

    private val _actions = SingleLiveEvent<Action>()
    val actions: MutableLiveData<Action> = _actions
    private val _login = MutableLiveData<String>("kmm2022")
    private val _password = MutableLiveData<String>("kmmIsSuper")
    val login: LiveData<String> = _login
    val password: LiveData<String> = _password
    private val _isLoginInputError = MutableLiveData(false)
    val isLoginInputError: LiveData<Boolean> = _isLoginInputError
    private val _isPasswordInputError = MutableLiveData(false)
    val isPasswordInputError: LiveData<Boolean> = _isPasswordInputError

    fun onLoginClick() {
        println("onLoginClick")
        viewModelScope.launch {
            when (val result = interactor.createSession(login.value.orEmpty(), password.value.orEmpty())) {
                is ResultSuccess -> {
                    Log.d(TAG, "Successful login: ${result.data.login}")
                    _isLoginInputError.value = false
                    _isPasswordInputError.value = false
                    _actions.value = Action.GoToMainPage
                }
                is ResultInputLoginArguments -> {
                    _isLoginInputError.value = false
                    _isPasswordInputError.value = false
                    _actions.value = Action.ShowSnackBar("Введите данные для входа")
                }
                is ResultInvalidArguments -> {
                    Log.w(TAG, "Invalid login arguments: ${result.invalidFields}")
                    val invalidFieldNames = result.invalidFields.map { it.field }
                    _isLoginInputError.value = "email" in invalidFieldNames
                    _isPasswordInputError.value = "password" in invalidFieldNames
                }
                is ResultException -> {
                    _isLoginInputError.value = false
                    _isPasswordInputError.value = false
                    _actions.value = Action.ShowSnackBar("Ошибка при выполнении запроса")
                    Log.w(TAG, result.e.message.orEmpty(), result.e)
                }
                is ResultApiError -> {
                    _isLoginInputError.value = false
                    _isPasswordInputError.value = false
                    val errorText = "Api error [${result.code}] ${result.message}"
                    _actions.value = Action.ShowSnackBar(errorText)
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
        _actions.value = Action.GoToRegistration
    }

    fun onForgetPassword() {
        _actions.value = Action.GoToForgotPassword
    }

    private companion object {
        const val TAG = "LoginViewModel"
    }

    sealed class Action {
        object GoToMainPage : Action()
        object GoToRegistration : Action()
        object GoToForgotPassword : Action()
        data class ShowSnackBar(val text: String) : Action()
    }
}
