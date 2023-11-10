package com.integrame.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.integrame.app.R
import com.integrame.app.ui.viewmodel.SplashScreenViewModel

@Composable
fun SplashScreen(
    onLoadReady: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    splashScreenViewModel: SplashScreenViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LaunchedEffect(Unit) {
            onLoadReady(splashScreenViewModel.hasAuthorizedSession())
        }

        Box(
            modifier = modifier
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(color = Color.Black),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo aplicación"
            )
        }
    }
}