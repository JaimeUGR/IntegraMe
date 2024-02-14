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

package com.integrame.app.ui.navigation

interface IntegraMeNavGraph {
    val route: String

    fun getSubRoute(subRoute: String) : String {
        return "$route/$subRoute"
    }
}

interface IntegraMeScreen {
    val route: String
}

object SplashScreenNav : IntegraMeScreen {
    override val route: String = "splash_screen"
}
