package com.nsp.investkraft.view.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nsp.investkraft.ui.theme.colorPrimary
import com.nsp.investkraft.ui.theme.colorWhite
import com.nsp.investkraft.ui.theme.poppinsFont

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorWhite),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Home Screen\nUnder Development",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorPrimary,
            fontFamily = poppinsFont
        )
    }
}
