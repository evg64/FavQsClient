package com.favqsclient.kmm.android.signup

import android.content.res.Resources
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.favqsclient.kmm.android.Destinations
import com.favqsclient.kmm.android.R

/**
 * Экран регистрации.
 * @author Maxim Filenkov
 */
@Composable
fun SignupScreen(
    viewModel: SignupViewModel,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
) {
    val login: State<String?> = viewModel.login.observeAsState()
    val email: State<String?> = viewModel.email.observeAsState()
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
            modifier = Modifier
                .align(alignment = Alignment.Start)
                .padding(
                    top = 16.dp,
                    bottom = 16.dp
                )
                .fillMaxWidth(),
            text = resources.getString(R.string.registration),
            style = LocalTextStyle.current.merge(
                TextStyle(
                    fontSize = 24.sp,
                )
            )
        )
        TextField(
            value = login.value.orEmpty(),
            onValueChange = { viewModel.onLoginChanged(it) },
            placeholder = { Text(resources.getString(R.string.login)) },
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                )
                .fillMaxWidth(),
        )
        TextField(
            value = email.value.orEmpty(),
            onValueChange = { viewModel.onEmailChanged(it) },
            placeholder = { Text(resources.getString(R.string.email)) },
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                )
                .fillMaxWidth(),
        )
        TextField(
            value = password.value.orEmpty(),
            onValueChange = { viewModel.onPasswordChanged(it) },
            placeholder = { Text(resources.getString(R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                )
                .fillMaxWidth(),
        )
        Button(
            onClick = { viewModel.onSignupClick() },
            modifier = Modifier.padding(
                top = 16.dp,
                end = 16.dp,
                bottom = 16.dp,
            ).fillMaxWidth(),
        ) {
            Text(
                text = stringResource(R.string.signup),
                style = LocalTextStyle.current.merge(
                    TextStyle(color = Color.White)
                ),
            )
        }

        val action: State<SignupViewModel.Action?> = viewModel.actions.observeAsState()
        HandleActions(action, navController, scaffoldState, viewModel)
    }
}

@Composable
fun HandleActions(
    action: State<SignupViewModel.Action?>,
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: SignupViewModel
) {
    when (val actionValue = action.value) {
        SignupViewModel.Action.GoToMainPage -> {
            navController.navigate(Destinations.MAIN_SCREEN)
        }
        is SignupViewModel.Action.ShowSnackBar -> {
            ShowSnackBar(scaffoldState.snackbarHostState, actionValue, viewModel.actions)
        }
        else -> {}
    }
}

@Composable
fun ShowSnackBar(
    snackbarHostState: SnackbarHostState,
    actionValue: SignupViewModel.Action.ShowSnackBar,
    actions: MutableLiveData<SignupViewModel.Action>,
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