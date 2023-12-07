package com.integrame.app.login.data.repository

import org.junit.Test

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.integrame.app.core.data.model.content.RemoteImage
import org.junit.Before
import org.junit.Rule
import com.integrame.app.tasks.domain.repository.GenericTaskRepository
import com.integrame.app.tasks.data.repository.GenericTaskRepositoryImpl
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue

class GenericTaskScreenTest {
    private lateinit var genericTaskRepository: GenericTaskRepository

    @Before
    fun setUp() {
        genericTaskRepository = GenericTaskRepositoryImpl()
    }

    @Test
    fun testGetTaskModel() {
        // Prueba para el método getTaskModel
        val taskId = 1
        val taskModel = genericTaskRepository.getTaskModel(taskId)

        // Verifica que el modelo de tarea se obtenga correctamente
        assertEquals(taskId, taskModel.taskId)
        assertEquals("Tarea Genérica 1", taskModel.displayName)
        // Asegúrate de verificar otras propiedades según tu implementación
    }

    @Test
    fun testGetNumSteps() {
        // Prueba para el método getNumSteps
        val taskId = 1
        val numSteps = genericTaskRepository.getNumSteps(taskId)

        // Verifica que el número de pasos se obtenga correctamente
        assertEquals(2, numSteps)
    }

    @Test
    fun testGetStep() {
        // Prueba para el método getStep
        val taskId = 1
        val stepNumber = 0
        val step = genericTaskRepository.getStep(taskId, stepNumber)

        // Verifica que el paso se obtenga correctamente
        assertEquals("Paso 0", step.name)
        assertFalse(step.isCompleted)
    }

    @Test
    fun testToggleStepCompleted() {
        // Prueba para el método toggleStepCompleted
        val taskId = 1
        val stepNumber = 0

        // Verifica el estado inicial del paso
        assertFalse(genericTaskRepository.getStep(taskId, stepNumber).isCompleted)

        // Realiza la acción de alternar la completitud del paso
        val isCompleted = genericTaskRepository.toggleStepCompleted(taskId, stepNumber)

        // Verifica que la acción se haya ejecutado correctamente
        assertTrue(isCompleted)
        // Verifica que el estado del paso haya cambiado
        assertTrue(genericTaskRepository.getStep(taskId, stepNumber).isCompleted)
    }

    @Test
    fun testGetReward() {
        // Prueba para el método getReward
        RemoteImage(
            imageUrl = "https://imgs.search.brave.com/nPvo3y2Adg-zq-UJjMmuz8edYP140xQI84q0UYb3a3s/rs:fit:860:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvY29v/bC1wcm9maWxlLXBp/Y3R1cmUtcWVqN2oy/ZWt1b3I5M3NzNy5q/cGc",
            0,
            "Carita sonriente"
        )

        val taskId = 1
        val reward = genericTaskRepository.getReward(taskId)

        // Verifica que la recompensa se obtenga correctamente
        assertTrue(reward is RemoteImage)
        assertEquals("Carita sonriente", (reward as RemoteImage).altDescription)
        // Asegúrate de verificar otras propiedades según tu implementación
    }
}
