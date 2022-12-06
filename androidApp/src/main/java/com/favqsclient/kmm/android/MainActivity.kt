package com.favqsclient.kmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.favqsclient.kmm.android.login.presentation.LoginScreen
import com.favqsclient.kmm.android.login.presentation.LoginViewModel
import com.favqsclient.kmm.android.login.presentation.LoginViewModelFactory

class MainActivity : ComponentActivity() {
    private val loginViewModelFactory = LoginViewModelFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    scaffoldState = scaffoldState,
                    modifier = Modifier.fillMaxSize(),
                ) { padding ->
                    Surface(
                        modifier = Modifier.fillMaxSize().padding(padding),
                        color = MaterialTheme.colors.background
                    ) {
                        val navController = rememberNavController()
                        NavigationGraph(navController, scaffoldState)
                    }
                }
            }
        }
    }

    @Composable
    fun NavigationGraph(
        navController: NavHostController,
        scaffoldState: ScaffoldState
    ) {
        NavHost(navController = navController, startDestination = Destinations.LOGIN) {
            composable(Destinations.LOGIN) {
                val viewModel: LoginViewModel = viewModel(
                    viewModelStoreOwner = this@MainActivity,
                    factory = loginViewModelFactory,
                )
                LoginScreen(viewModel, scaffoldState, navController)
            }
            composable(Destinations.REGISTRATION) {
                TODO("Implement me")
            }
            composable(Destinations.FORGOT_PASSWORD) {
                TODO("Implement me")
            }
            composable(Destinations.MAIN_SCREEN) {

            }
            composable(Destinations.DETAILS) {

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
