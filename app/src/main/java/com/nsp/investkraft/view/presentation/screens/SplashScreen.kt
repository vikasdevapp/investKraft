package com.nsp.investkraft.view.presentation.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.nsp.investkraft.R
import com.nsp.investkraft.view.route.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController) {
    val scaleAnim = remember { Animatable(0.3f) }
    val alphaAnim = remember { Animatable(0f) }
    val rotateAnim = remember { Animatable(-15f) }
    var hasNavigated by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        launch {
            alphaAnim.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 1200,
                    easing = LinearOutSlowInEasing
                )
            )
        }

        launch {
            scaleAnim.animateTo(
                1.1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            scaleAnim.animateTo(
                1f,
                animationSpec = tween(400, easing = FastOutSlowInEasing)
            )
        }

        launch {
            rotateAnim.animateTo(
                0f,
                animationSpec = tween(
                    durationMillis = 1400,
                    easing = FastOutSlowInEasing
                )
            )
        }

        delay(2700)

        if (!hasNavigated) {
            hasNavigated = true
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Splash.route) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        // Lottie Loader Background
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loader))
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.Center)
        )

        // Logo in front
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "InvestKraft Logo",
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer(
                    scaleX = scaleAnim.value,
                    scaleY = scaleAnim.value,
                    alpha = alphaAnim.value,
                    rotationZ = rotateAnim.value
                )
        )
    }

}
