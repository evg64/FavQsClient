package com.favqsclient.kmm.android.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
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
import com.favqsclient.kmm.android.R

/**
 *
 *
 * @author Evgeny Chumak
 **/

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
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
        OutlinedButton(
            onClick = { viewModel.onForgetPassword() },
            modifier = Modifier.align(Alignment.CenterHorizontally),
        ) {
            Text(
                text = stringResource(R.string.forgot_password)
            )
        }
    }
}