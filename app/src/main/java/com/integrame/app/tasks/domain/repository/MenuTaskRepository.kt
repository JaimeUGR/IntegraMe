package com.integrame.app.tasks.domain.repository

import android.icu.util.CurrencyAmount
import com.integrame.app.tasks.data.model.ClassroomMenuTask
import com.integrame.app.tasks.data.model.MenuOption
import com.integrame.app.tasks.data.model.MenuTaskModel

interface MenuTaskRepository {
    // ¿Qué necesito de la base de datos?
    /*
        Para la selección del aula necesito:
            - Una lista con las letras de las disntintas clases

        Para la selección de menús:
            - La letra de la clase
            - Lista de imágenes con los menús
            - Las imágenes de los dedos
     */

    // Para obtener el objeto del modelo de la tarea del menu
    fun getMenuTaskModel(taskId: Int): MenuTaskModel

    // Para sacar la lista de clases que tiene la tarea de menú asociada
    fun getClassroomMenus(taskId: Int): List<ClassroomMenuTask>

    // Para sacar la letra de cada clase
    fun getClassroomId(taskId: Int, classroomId: Int): Int

    // Para sacar la lista de menús de cada clase
    fun getMenuOptions(taskId: Int, classroomId: Int): List<MenuOption>

    // ¿Qué tengo que introducir en la base de datos?
    /*
        Para una clase determinada, una lista que contenga el menú seleccionado y la cantidad que se requiere del mismo
     */
    fun setRequestedAmount(taskId: Int, classroomId: Int,name: String, requestedAmount: Int)
}