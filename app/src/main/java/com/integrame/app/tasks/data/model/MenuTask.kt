package com.integrame.app.tasks.data.model

import android.icu.util.CurrencyAmount
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.RemoteImage
import kotlinx.serialization.Serializable

data class MenuTaskModel(
    override val taskId: Int,
    override val displayName: String,
    override val displayImage: ImageContent
): TaskModel()

@Serializable
data class MenuTask(
    override val taskId: Int,
    override val displayName: String,
    override val displayImage: ImageContent,
    val classroomMenus: List<ClassroomMenuTask>
): Task()

@Serializable
data class ClassroomMenuTask(
    val classroomId: Int, // Letra de la clase
    val menuOptions: List<MenuOption> // Lista de comida
)

@Serializable
data class MenuOption(
    val name: String, // Nombre de la comida
    val image: ImageContent // Imagen asociada a la comida
) {
    var requestedAmount: Int = 0 // Cantidad de comida escogida
        private set

    fun setRequestAmount(amount: Int){
        requestedAmount = amount
    }
}
