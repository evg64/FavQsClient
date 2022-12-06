package com.favqsclient.kmm.android.login.presentation

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.favqsclient.kmm.android.Destinations
import com.favqsclient.kmm.android.R

/**
 *
 *
 * @author Evgeny Chumak
 **/

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
) {
    val login: State<String?> = viewModel.login.observeAsState()
    val password: State<String?> = viewModel.password.observeAsState()
    val resources = LocalContext.current.resources
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(
            start = 30.dp,
            top = 16.dp,
            end = 30.dp,
            bottom = 16.dp,
        )
    ) {
        Text(
            modifier = Modifier.align(alignment = Alignment.Start)
                .padding(
                    top = 16.dp,
                    bottom = 16.dp
                ).fillMaxWidth(),
            text = resources.getString(R.string.welcome),
            style = LocalTextStyle.current.merge(
                TextStyle(
                    fontSize = 24.sp,
                )
            )
        )
        LoginPassword(login, viewModel, resources, password)
        LoginRegisterButtons(viewModel)
        OutlinedButton(
            onClick = { viewModel.onForgetPassword() },
            modifier = Modifier.align(Alignment.CenterHorizontally),
        ) {
            Text(
                text = stringResource(R.string.forgot_password)
            )
        }

        val action: State<LoginViewModel.Action?> = viewModel.actions.observeAsState()
        HandleActions(action, navController, scaffoldState, viewModel)
    }
}

@Composable
fun LoginPassword(
    login: State<String?>,
    viewModel: LoginViewModel,
    resources: Resources,
    password: State<String?>
) {
    TextField(
        value = login.value.orEmpty(),
        onValueChange = { viewModel.onLoginChanged(it) },
        placeholder = { Text(resources.getString(R.string.login_or_email)) },
        modifier = Modifier.padding(
            top = 16.dp,
            bottom = 16.dp,
        ).fillMaxWidth(),
    )
    TextField(
        value = password.value.orEmpty(),
        onValueChange = { viewModel.onPasswordChanged(it) },
        placeholder = { Text(resources.getString(R.string.password)) },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.padding(
            top = 16.dp,
            bottom = 16.dp,
        ).fillMaxWidth(),
    )
}

@Composable
fun LoginRegisterButtons(viewModel: LoginViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Button(
            onClick = { viewModel.onLoginClick() },
            modifier = Modifier.weight(1f)
                .padding(
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                ),
        ) {
            Text(
                text = stringResource(R.string.enter),
                style = LocalTextStyle.current.merge(
                    TextStyle(color = Color.White)
                ),
            )
        }
        OutlinedButton(
            onClick = { viewModel.onRegisterClick() },
            modifier = Modifier.weight(1f)
                .padding(
                    start = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp,
                ),
        ) {
            Text(
                text = stringResource(R.string.registration),
                modifier = Modifier.background(color = Color.Transparent),
            )
        }
    }
}

@Composable
fun HandleActions(
    action: State<LoginViewModel.Action?>,
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: LoginViewModel
) {
    when (val actionValue = action.value) {
        LoginViewModel.Action.GoToMainPage -> {
            navController.navigate(Destinations.MAIN_SCREEN)
        }
        LoginViewModel.Action.GoToRegistration -> {
            navController.navigate(Destinations.REGISTRATION)
        }
        LoginViewModel.Action.GoToForgotPassword -> {
            navController.navigate(Destinations.FORGOT_PASSWORD)
        }
        is LoginViewModel.Action.ShowSnackBar -> {
            ShowSnackBar(scaffoldState.snackbarHostState, actionValue, viewModel.actions)
        }
        else -> {}
    }
}

@Composable
fun ShowSnackBar(
    snackbarHostState: SnackbarHostState,
    actionValue: LoginViewModel.Action.ShowSnackBar,
    actions: MutableLiveData<LoginViewModel.Action>,
) {
    LaunchedEffect(snackbarHostState) {
        val result = snackbarHostState.showSnackbar(
            message = actionValue.text,
        )
        when (result) {
            SnackbarResult.Dismissed -> {
                actions.value = null
            }
            else -> {}
        }
    }
}