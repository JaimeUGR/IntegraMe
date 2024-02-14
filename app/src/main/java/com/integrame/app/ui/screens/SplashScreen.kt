/*
 *
 * Copyright (c) IntegraMe - Champions 2024 - All rights reserved
 *
 *  You may use, distribute and modify this code under the terms of the
 *  Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0) license.
 *
 *  You should have received a copy of the CC BY-NC 4.0 license. Otherwise, read
 *  the full license text here: https://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 *  This file belongs to the project IntegraMe: https://github.com/JaimeUGR/IntegraMe
 */

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
            splashScreenViewModel.initialize()
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