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
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.favqsclient.kmm.android.login.presentation.LoginScreen
import com.favqsclient.kmm.android.login.presentation.LoginViewModel
import com.favqsclient.kmm.android.login.presentation.LoginViewModelFactory
import com.favqsclient.kmm.android.mainScreen.presentation.MainScreen
import com.favqsclient.kmm.android.mainScreen.presentation.MainScreenViewModel
import com.favqsclient.kmm.android.mainScreen.presentation.MainScreenViewModelFactory
import com.favqsclient.kmm.android.signup.SignupScreen
import com.favqsclient.kmm.android.signup.SignupViewModel
import com.favqsclient.kmm.android.signup.SignupViewModelFactory

class MainActivity : ComponentActivity() {
    private val loginViewModelFactory = LoginViewModelFactory()
    private val mainScreenViewModelFactory = MainScreenViewModelFactory()
    private val signupViewModelFactory = SignupViewModelFactory()

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
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
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
                val viewModel: SignupViewModel = viewModel(
                    viewModelStoreOwner = this@MainActivity,
                    factory = signupViewModelFactory
                )
                SignupScreen(viewModel, scaffoldState, navController)
            }
            composable(Destinations.FORGOT_PASSWORD) {
                TODO("Implement me")
            }
            composable(Destinations.MAIN_SCREEN) {
                val viewModel: MainScreenViewModel = viewModel(
                    viewModelStoreOwner = this@MainActivity,
                    factory = mainScreenViewModelFactory,
                )
                MainScreen(
                    viewModel = viewModel,
                    scaffoldState = scaffoldState,
                    navController = navController,
                )
            }
            composable(Destinations.DETAILS) {
                TODO("Implement me")
            }
        }
    }
}