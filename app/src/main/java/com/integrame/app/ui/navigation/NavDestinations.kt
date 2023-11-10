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
