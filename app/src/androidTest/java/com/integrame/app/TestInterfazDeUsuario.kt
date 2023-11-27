package com.integrame.app

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.onSibling
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.performTouchInput
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
class TestInterfazDeUsuario {
    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testNavegacionLoginAlumnoALoginProfesor() {

        rule.waitForIdle()
        val node1 = rule.onNodeWithContentDescription("Identificación Profesor", true, true, true).assertExists("No se encontró el elemento")
        node1.onParent().assertHasClickAction()
        node1.onParent().performClick()

        rule.waitForIdle()
        val node2 = rule.onNodeWithContentDescription("Retroceder").assertExists("No existe el botón de retroceso")
        node2.assertHasClickAction()
        node2.performClick()
    }

    //Necesito que las tarjetas de cada alumno tengan un content descriptión
    @Test
    fun testSeleccionEnLaListaDeAlumnos() {

        rule.waitForIdle()
        val node1 = rule.onNodeWithContentDescription("Identificación Profesor", true, true, true).assertExists("No se encontró el elemento")
        node1.onParent().assertHasClickAction()
        node1.onParent().performClick()

        val nodeName = rule.onAllNodesWithText("Usuario", true,true,true)[1].assertExists("No se encontró el elemento")

        val nodePassword = rule.onAllNodesWithText("Contraseña", true,true,true)[1].assertExists("No se encontró el elemento")

        val nodeLogin = rule.onNodeWithText("Iniciar Sesión", true,true,true).assertExists("No se encontró el elemento")
        nodeLogin.onParent().assertHasClickAction()
        nodeLogin.onParent().performClick()
    }







}