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

    override suspend fun getMenuTaskModel(taskId: Int): MenuTaskModel {
       /* return MenuTaskModel(
            taskId = taskId,
            // TODO: Poner el nombre e imagen de menuTask
            displayName = FakeResources.genericTasks[4].displayName,
            displayImage = FakeResources.genericTasks[4].displayImage,
            reward = FakeResources.genericTasks[taskId].reward,
        )

        */
        return api.getMenuTaskModel(taskId)
    }

    /*
    // Para sacar la lista de clases que tiene la tarea de menú asociada
    override suspend fun getClassroomMenus(taskId: Int): List<ClassroomMenuTask> {
        //return FakeResources.menuTasks[taskId].classroomMenus
        return api.getClassroomMenus(taskId)
    }
     */

    // Para sacar la lista de ids de las aulas de las clases
    override suspend fun getClassroomIds(): List<Int> {
        //val classroomList = FakeResources.menuTasks[taskId].classroomMenus
        /*
        val classroomList = api.getClassroomMenus(taskId)

        // Utiliza map para obtener los ids de todas las ClassroomMenuTask en la lista
        return classroomList.map { classroomMenuTask ->
            classroomMenuTask.classroomId
        }

         */
        return api.getClassroomIds()
    }

    // Para sacar la lista de menús de cada clase
    override suspend fun getMenuOptions(taskId: Int, classroomId: Int): List<MenuOption> {
        //return FakeResources.menuTasks[taskId].classroomMenus[classroomId].menuOptions
        return api.getMenuOptions(taskId, classroomId)
    }

    // ¿Qué tengo que introducir en la base de datos?
    /*
        Para una clase determinada, una lista que contenga el menú seleccionado y la cantidad que se requiere del mismo
     */
    override suspend fun setRequestedAmount(taskId: Int, classroomId: Int, name: String, requestedAmount: Int) {
        //val taskMenuOption = FakeResources.menuTasks[taskId].classroomMenus[classroomId].menuOptions

        val taskMenuOptionAPI = api.getMenuOptions(taskId, classroomId)

        if(taskMenuOptionAPI.isNotEmpty()){
            // Encuentra la opción de menú específica por el nombre de la comida
            val menuOption = taskMenuOptionAPI.find { it.name == name  }

            // Actualiza la cantidad si se encontró la opción de menú
            menuOption?.setRequestAmount(requestedAmount)
        }

        //api.postSetRequestAmount(taskId, classroomId, name, requestedAmount)

    }

}