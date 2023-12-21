package com.integrame.app.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.integrame.app.ui.navigation.IntegraMeNavHost
import com.integrame.app.ui.theme.IntegraMeTheme

//
// TODO: Rework de carga de datos
//
/*
    Actualmente se carga el tema sobre todas las pantallas, incluyendo la splash screen.
    Si ese tema fuera dinámico, a través de la red o hubiera que cargar una fuente, la aplicación
    se quedaría congelada o podrían verse artefactos / fuentes por defecto.


    Por eso, esa carga debería hacerse mientras se muestra la splash screen. Habría que añadir a esta
    función un viewmodel encargado de la carga y que simplemente sea if (splash) SplashScreen else NavHost

    En el NavHost, se recibiría como parámetro el isLoggedIn y en la ruta splashscreen, se haría un
    LaunchedEffect que según el valor de esa variable, redijira a una ruta u otra.


    Además, tener un viewmodel en esta pantalla, permitiría hacer recomposiciones completas de toda
    la aplicación.
    En ese viewmodel, estaría un acceso a un repositorio que tenga fuentes y recursos dinámicos que,
    cuando se haga la carga, simplemente se hace viewmodel.getXRecurso y se pasa a las funciones
    que lo necesiten.
 */
@Composable
fun IntegraMeApp() {
    IntegraMeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            IntegraMeNavHost(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
