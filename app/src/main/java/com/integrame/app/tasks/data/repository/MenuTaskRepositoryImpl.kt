package com.integrame.app.tasks.data.repository

import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.data.network.api.IntegraMeApi
import com.integrame.app.tasks.data.model.ClassroomMenuTask
import com.integrame.app.tasks.data.model.GenericTaskModel
import com.integrame.app.tasks.data.model.MenuOption
import com.integrame.app.tasks.data.model.MenuTaskModel
import com.integrame.app.tasks.domain.repository.MenuTaskRepository

class MenuTaskRepositoryImpl(
    private val api: IntegraMeApi
): MenuTaskRepository {

    override fun getMenuTaskModel(taskId: Int): MenuTaskModel{
        return MenuTaskModel(
            taskId = taskId,
            // TODO: Poner el nombre e imagen de menuTask
            displayName = FakeResources.genericTasks[4].displayName,
            displayImage = FakeResources.genericTasks[4].displayImage,
        )

    }

    // Para sacar la lista de clases que tiene la tarea de menú asociada
    override fun getClassroomMenus(taskId: Int): List<ClassroomMenuTask>{
        return FakeResources.menuTasks[taskId].classroomMenus

    }

    // Para sacar la letra de cada clase
    override fun getClassroomId(taskId: Int, classroomId: Int): Int {
        return FakeResources.menuTasks[taskId].classroomMenus[classroomId].classroomId

    }

    // Para sacar la lista de menús de cada clase
    override fun getMenuOptions(taskId: Int, classroomId: Int): List<MenuOption>{
        return FakeResources.menuTasks[taskId].classroomMenus[classroomId].menuOptions

    }

    // ¿Qué tengo que introducir en la base de datos?
    /*
        Para una clase determinada, una lista que contenga el menú seleccionado y la cantidad que se requiere del mismo
     */
    override fun setRequestedAmount(taskId: Int, classroomId: Int,name: String, requestedAmount: Int){
        val taskMenuOption = FakeResources.menuTasks[taskId].classroomMenus[classroomId].menuOptions

        if(taskMenuOption.isNotEmpty()){
            // Encuentra la opción de menú específica por el nombre de la comida
            val menuOption = taskMenuOption.find { it.name == name  }

            // Actualiza la cantidad si se encontró la opción de menú
            menuOption?.let {
                it.setRequestAmount(requestedAmount)
            }
        }

    }

}