package com.favqsclient.kmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.favqsclient.kmm.android.login.presentation.LoginScreen
import com.favqsclient.kmm.android.login.presentation.LoginViewModel
import com.favqsclient.kmm.android.login.presentation.LoginViewModelFactory

class MainActivity : ComponentActivity() {
    private val loginViewModelFactory = LoginViewModelFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel: LoginViewModel = viewModel(
                        viewModelStoreOwner = this,
                        factory = loginViewModelFactory,
                    )
                    LoginScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
