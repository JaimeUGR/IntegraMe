package com.integrame.app.core.ui.navigation

interface IntegraMeGraph {
    val route: String

    fun getSubRoute(subRoute: String) : String {
        return "$route/$subRoute"
    }
}

interface IntegraMeScreen {
    val route: String
}

// TODO: Mover a su feature_folder correspondiente
object SplashScreen : IntegraMeScreen {
    override val route: String = "splash_screen"
}
