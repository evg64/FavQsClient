package com.favqsclient.kmm.android.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.favqsclient.kmm.android.core.SingleLiveEvent
import com.favqsclient.kmm.domain.Interactor
import com.favqsclient.kmm.domain.entity.ResultApiError
import com.favqsclient.kmm.domain.entity.ResultException
import com.favqsclient.kmm.domain.entity.ResultInvalidArguments
import com.favqsclient.kmm.domain.entity.ResultSuccess
import kotlinx.coroutines.launch

/**
 * Вьюмодель экрана регистрации.
 * @author Maxim Filenkov
 */
class SignupViewModel(
    private val interactor: Interactor,
) : ViewModel() {

    private val _actions = SingleLiveEvent<Action>()
    val actions: MutableLiveData<Action> = _actions
    private val _login = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    val login: LiveData<String> = _login
    val email: LiveData<String> = _email
    val password: LiveData<String> = _password

    fun onSignupClick() {
        println("onSignupClick")
        viewModelScope.launch {
            when (val result = interactor.createUser(
                login.value.orEmpty(), email.value.orEmpty(), password.value.orEmpty()
            )) {
                is ResultSuccess -> {
                    Log.d(TAG, "Successful login: ${result.data.login}")
                    _actions.value = Action.GoToMainPage
                }
                is ResultInvalidArguments -> {
                    Log.w(TAG, "Invalid login arguments: ${result.invalidFields}")
                    _actions.value = Action.ShowSnackBar("Введите данные для входа")
                }
                is ResultException -> {
                    _actions.value = Action.ShowSnackBar("Ошибка при выполнении запроса")
                    Log.w(TAG, result.e.message.orEmpty(), result.e)
                }
                is ResultApiError -> {
                    val errorText = "Api error [${result.code}] ${result.message}"
                    _actions.value = Action.ShowSnackBar(errorText)
                    println("Api error [${result.code}] ${result.message}")
                }
                else -> {}
            }
        }
    }

    fun onLoginChanged(newLogin: String) {
        _login.value = newLogin
    }

    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }

    private companion object {
        const val TAG = "SignupViewModel"
    }

    sealed class Action {
        object GoToMainPage : Action()
        data class ShowSnackBar(val text: String) : Action()
    }
}
