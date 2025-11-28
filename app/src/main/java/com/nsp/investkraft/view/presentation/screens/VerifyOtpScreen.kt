package com.nsp.investkraft.view.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun PreviewOtpScreen() {
    VerifyOtpScreen(rememberNavController(), "9876543210")
}

@Composable
fun VerifyOtpScreen(navController: NavController, phoneNumber: String = "") {
    var otp1 by remember { mutableStateOf("") }
    var otp2 by remember { mutableStateOf("") }
    var otp3 by remember { mutableStateOf("") }
    var otp4 by remember { mutableStateOf("") }
    var timer by remember { mutableStateOf(39) }

    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val focusRequester3 = remember { FocusRequester() }
    val focusRequester4 = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()
    var isLoading by remember { mutableStateOf(false) }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = colorPrimary,
            darkIcons = true
        )
    }

    LaunchedEffect(Unit) {
        focusRequester1.requestFocus()
    }

    LaunchedEffect(timer) {
        if (timer > 0) {
            delay(1000L)
            timer--
        }
    }

    val maskedPhone = if (phoneNumber.length >= 4) {
        "******${phoneNumber.takeLast(4)}"
    } else {
        phoneNumber
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

            Image(
                painter = painterResource(id = R.drawable.verifyheader),
                contentDescription = "OTP illustration",
                modifier = Modifier
                    .size(300.dp)
                    .padding(24.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "Verify Mobile Number",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = colorBlack,
                fontFamily = poppinsFont,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "OTP sent to ",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontFamily = poppinsFont
                )
                Text(
                    text = maskedPhone,
                    fontSize = 14.sp,
                    color = colorBlack,
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Medium
                )
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit phone number",
                        tint = colorPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OtpInputBox(
                    value = otp1,
                    onValueChange = {
                        if (it.length <= 1) {
                            otp1 = it
                            if (it.isNotEmpty()) {
                                focusRequester2.requestFocus()
                            }
                        }
                    },
                    focusRequester = focusRequester1
                )
                Spacer(modifier = Modifier.width(16.dp))
                OtpInputBox(
                    value = otp2,
                    onValueChange = {
                        if (it.length <= 1) {
                            otp2 = it
                            if (it.isNotEmpty()) {
                                focusRequester3.requestFocus()
                            }
                        } else if (it.isEmpty() && otp2.isNotEmpty()) {
                            otp2 = ""
                            focusRequester1.requestFocus()
                        }
                    },
                    focusRequester = focusRequester2,
                    onBackspace = {
                        if (otp2.isEmpty()) {
                            focusRequester1.requestFocus()
                        }
                    }
                )
                Spacer(modifier = Modifier.width(16.dp))
                OtpInputBox(
                    value = otp3,
                    onValueChange = {
                        if (it.length <= 1) {
                            otp3 = it
                            if (it.isNotEmpty()) {
                                focusRequester4.requestFocus()
                            }
                        } else if (it.isEmpty() && otp3.isNotEmpty()) {
                            otp3 = ""
                            focusRequester2.requestFocus()
                        }
                    },
                    focusRequester = focusRequester3,
                    onBackspace = {
                        if (otp3.isEmpty()) {
                            focusRequester2.requestFocus()
                        }
                    }
                )
                Spacer(modifier = Modifier.width(16.dp))
                OtpInputBox(
                    value = otp4,
                    onValueChange = {
                        if (it.length <= 1) {
                            otp4 = it
                            if (it.isNotEmpty()) {
                                focusManager.clearFocus()
                            }
                        } else if (it.isEmpty() && otp4.isNotEmpty()) {
                            otp4 = ""
                            focusRequester3.requestFocus()
                        }
                    },
                    focusRequester = focusRequester4,
                    onBackspace = {
                        if (otp4.isEmpty()) {
                            focusRequester3.requestFocus()
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    isLoading = true
                    scope.launch {
                        delay(1000)
                        isLoading = false
                        navController.navigate("home") {
                            popUpTo("otp") { inclusive = true }
                        }
                    }
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
                enabled = otp1.isNotEmpty() && otp2.isNotEmpty() &&
                        otp3.isNotEmpty() && otp4.isNotEmpty() && !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text(
                        text = "Verify OTP",
                        fontSize = 16.sp,
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun OtpInputBox(
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    onBackspace: () -> Unit = {}
) {
    val borderColor = if (value.isNotEmpty()) colorBlack else Color.Gray.copy(alpha = 0.4f)

    Box(
        modifier = Modifier
            .size(48.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 1.5.dp,
                color = borderColor,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = value,
            onValueChange = { newValue ->
                when {
                    newValue.isEmpty() && value.isNotEmpty() -> {
                        onValueChange("")
                        onBackspace()
                    }
                    newValue.length == 1 && newValue.all { it.isDigit() } -> {
                        onValueChange(newValue)
                    }
                    newValue.isEmpty() -> {
                        onValueChange("")
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = colorBlack,
                fontFamily = poppinsFont
            ),
            modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    innerTextField()
                }
            }
        )
    }
}
