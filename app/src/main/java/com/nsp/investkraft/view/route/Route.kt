package com.nsp.investkraft.view.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nsp.investkraft.view.presentation.screens.LoginScreen
import com.nsp.investkraft.view.presentation.screens.SplashScreen
import com.nsp.investkraft.view.presentation.screens.VerifyOtpScreen

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Login : Screen("login")
    data object VerifyOtp : Screen("verifyOtpScreen/{phoneNumber}") {
        fun createRoute(phoneNumber: String) = "verifyOtpScreen/$phoneNumber"
    }
}

@Composable
fun TypeSafeNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(
            route = Screen.VerifyOtp.route,
            arguments = listOf(
                navArgument("phoneNumber") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            VerifyOtpScreen(navController, phoneNumber)
        }
    }
}