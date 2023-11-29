package com.integrame.app

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
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
    @Test
    fun testSeleccionAlumno() {
        rule.waitForIdle()
        rule.waitUntil {
            rule
                .onAllNodesWithTag("Tarjeta alumno")
                .fetchSemanticsNodes().size > 0
        }
        val node1 = rule.onAllNodesWithTag("Tarjeta alumno", true)[0].assertExists("No se encontró el elemento")
        node1.onChildAt(0).assertHasClickAction()
        node1.onChildAt(0).performClick()
        rule.waitForIdle()
        rule.waitUntil(timeoutMillis = 20000) {
            rule
                .onAllNodesWithContentDescription("Retroceder")
                .fetchSemanticsNodes().size > 0
        }
        val node2 = rule.onNodeWithContentDescription("Retroceder", true).assertExists("No se encontró el elemento")
        node2.assertHasClickAction()
        node2.performClick()
    }
    @Test
    fun testLoginAlumnoText() {
        rule.waitForIdle()
        rule.waitUntil(timeoutMillis = 20000) {
            rule
                .onAllNodesWithTag("Tarjeta alumno")
                .fetchSemanticsNodes().size > 0
        }
        val node1 = rule.onNodeWithTag("Tarjeta alumno", true).assertExists("No se encontró el elemento")
        node1.onChildAt(0).assertHasClickAction()
        node1.onChildAt(0).performClick()
        rule.waitForIdle()
        rule.waitUntil(timeoutMillis = 20000) {
            rule
                .onAllNodesWithTag("textPassword")
                .fetchSemanticsNodes().size > 0
        }
        val nodeTextPassword = rule.onNodeWithTag("textPassword", true).assertExists("No se encontró el elemento")
        nodeTextPassword.performTextInput("integrame")
        rule.waitForIdle()
        rule.waitUntil(timeoutMillis = 20000) {
            rule
                .onAllNodesWithTag("signInButton")
                .fetchSemanticsNodes().size > 0
        }
        val nodeSignInButton = rule.onNodeWithTag("signInButton", true).assertExists("No se encontró el elemento")
        nodeSignInButton.assertHasClickAction()
        nodeSignInButton.performClick()
        rule.waitForIdle()
        rule.waitForIdle()
        rule.waitUntil(timeoutMillis = 20000) {
            rule
                .onAllNodesWithText("Actividades")
                .fetchSemanticsNodes().size > 0
        }
        rule.onNodeWithText("Actividades", true).assertExists("No se encontró el elemento")
    }
    @Test
    fun testLoginAlumnoImage() {
        rule.waitForIdle()
        rule.waitUntil(timeoutMillis = 20000) {
            rule
                .onAllNodesWithTag("Tarjeta alumno")
                .fetchSemanticsNodes().size > 0
        }
        val node1 = rule.onNodeWithTag("Tarjeta alumno", true).assertExists("No se encontró el elemento")
        node1.onChildAt(1).assertHasClickAction()
        node1.onChildAt(1).performClick()
        rule.waitForIdle()
        rule.waitUntil(timeoutMillis = 20000) {
            rule
                .onAllNodesWithTag("imagen contrasenia")
                .fetchSemanticsNodes().size > 0
        }
        val nodeImage = rule.onAllNodesWithTag("imagen contrasenia", true)[0].assertExists("No se encontró el elemento")
        nodeImage.assertHasClickAction()
        nodeImage.performClick()
        nodeImage.performClick()
        nodeImage.performClick()
        rule.waitForIdle()
        rule.waitUntil(timeoutMillis = 20000) {
            rule
                .onAllNodesWithText("Actividades")
                .fetchSemanticsNodes().size > 0
        }
        rule.onNodeWithText("Actividades", true).assertExists("No se encontró el elemento")
    }
    @Test
    fun testLoginProfesor() {
        rule.waitForIdle()
        val node1 = rule.onNodeWithContentDescription("Identificación Profesor", true, true, true).assertExists("No se encontró el elemento")
        node1.onParent().assertHasClickAction()
        node1.onParent().performClick()
        rule.waitForIdle()
        val nodeName = rule.onNodeWithTag("textUser", true).assertExists("No se encontró el elemento")
        nodeName.performTextInput("francx11")
        val nodePassword = rule.onNodeWithTag("textPassword", true).assertExists("No se encontró el elemento")
        nodePassword.performTextInput("integrame")
        val nodeLogin = rule.onNodeWithTag("signInButton", true).assertExists("No se encontró el elemento")
        nodeLogin.assertHasClickAction()
        nodeLogin.performClick()
    }
    @Test
    fun testDashboardAlumno() {
        rule.waitForIdle()
        rule.waitUntil(timeoutMillis = 20000) {
            rule
                .onAllNodesWithTag("Tarjeta alumno")
                .fetchSemanticsNodes().size > 0
        }
        val node1 = rule.onNodeWithTag("Tarjeta alumno", true).assertExists("No se encontró el elemento")
        node1.onChildAt(1).assertHasClickAction()
        node1.onChildAt(1).performClick()
        rule.waitForIdle()
        rule.waitUntil(timeoutMillis = 20000) {
            rule
                .onAllNodesWithTag("imagen contrasenia")
                .fetchSemanticsNodes().size > 0
        }
        val nodeImage = rule.onAllNodesWithTag("imagen contrasenia", true)[0].assertExists("No se encontró el elemento")
        nodeImage.assertHasClickAction()
        nodeImage.performClick()
        nodeImage.performClick()
        nodeImage.performClick()
        rule.waitForIdle()
        rule.waitUntil(timeoutMillis = 20000) {
            rule
                .onAllNodesWithText("Actividades")
                .fetchSemanticsNodes().size > 0
        }
        rule.onNodeWithText("Actividades", true).assertExists("No se encontró el elemento")
        rule.waitForIdle()
        rule.waitUntil(timeoutMillis = 20000) {
            rule
                .onAllNodesWithTag("TaskCard")
                .fetchSemanticsNodes().size > 0
        }
        val nodeTask = rule.onAllNodesWithTag("TaskCard", true)[0].assertExists("No se encontró el elemento")
        nodeTask.onChildAt(1).assertHasClickAction()
        nodeTask.onChildAt(1).performClick()
        rule.waitForIdle()
        rule.waitUntil(timeoutMillis = 20000) {
            rule
                .onAllNodesWithContentDescription("Retroceder")
                .fetchSemanticsNodes().size > 0
        }
        val nodeBack = rule.onAllNodesWithContentDescription("Retroceder", true)[0].assertExists("No se encontró el elemento")
        nodeBack.assertHasClickAction()
        nodeBack.performClick()
    }
}