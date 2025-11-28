package com.nsp.investkraft.view.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nsp.investkraft.R
import com.nsp.investkraft.ui.theme.colorBlack
import com.nsp.investkraft.ui.theme.colorPrimary
import com.nsp.investkraft.ui.theme.colorWhite
import com.nsp.investkraft.ui.theme.poppinsFont

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(rememberNavController())
}

@Composable
fun LoginScreen(navController: NavController) {
    var phoneNum by remember { mutableStateOf("") }
    val systemUiController = rememberSystemUiController()


    SideEffect {
        systemUiController.setStatusBarColor(
            color = colorPrimary,
            darkIcons = true
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorWhite)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Illustration Image
            Image(
                painter = painterResource(id = R.drawable.loginheader),
                contentDescription = "Mobile illustration",
                modifier = Modifier
                    .size(300.dp)
                    .padding(24.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            // Phone Input Field
            OutlinedTextField(
                value = phoneNum,
                onValueChange = {
                    if (it.length <= 10 && it.all { char -> char.isDigit() }) {
                        phoneNum = it
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Enter Your Mobile Number",
                        fontFamily = poppinsFont,
                        color = Color.Gray.copy(alpha = 0.5f),
                        fontSize = 14.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = android.R.drawable.stat_sys_phone_call),
                        contentDescription = "Phone",
                        tint = colorPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorBlack,
                    unfocusedBorderColor = colorBlack,
                    focusedTextColor = colorBlack,
                    unfocusedTextColor = colorBlack
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))


            Button(
                onClick = {
                    navController.navigate("verifyOtpScreen/$phoneNum")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorBlack,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray.copy(alpha = 0.3f)
                ),
                shape = RoundedCornerShape(12.dp),
                enabled = phoneNum.length == 10
            ) {
                Text(
                    text = "Get OTP",
                    fontSize = 16.sp,
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Medium
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            val termsText = buildAnnotatedString {
                append("By continuing you agree to our ")
                pushStringAnnotation(tag = "terms", annotation = "terms")
                withStyle(style = SpanStyle(color = colorPrimary, fontWeight = FontWeight.Medium)) {
                    append("Terms & Conditions")
                }
                pop()
            }

            Text(
                text = termsText,
                fontSize = 12.sp,
                fontFamily = poppinsFont,
                textAlign = TextAlign.Center
            )
        }
    }
}