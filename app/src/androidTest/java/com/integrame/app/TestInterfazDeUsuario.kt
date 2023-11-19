package com.integrame.app

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performClick
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
    fun testNavegacionLoginAlumnoALiginProfesor() {

        rule.waitForIdle()
        val node1 = rule.onNodeWithContentDescription("Identificación Profesor", true, true, true).assertExists("No se encontró el elemento")
        node1.onParent().assertHasClickAction()
        node1.onParent().performClick()

        rule.waitForIdle()
        val node2 = rule.onNodeWithContentDescription("Retroceder").assertExists("No existe el botón de retroceso")
        node2.assertHasClickAction()
        node2.performClick()
    }





}