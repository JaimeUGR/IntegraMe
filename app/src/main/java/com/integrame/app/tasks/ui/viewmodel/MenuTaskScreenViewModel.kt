package com.integrame.app.tasks.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.tasks.data.model.MenuTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {

    private val _menuDayState = MutableStateFlow<MenuTask?>(null)
    val menuDayState: StateFlow<MenuTask?> get() = _menuDayState

    fun loadMenuDay(date: String) {

        }
    }

    private fun getMenuDayFromRepository(date: String): MenuTask {
        // Aquí obtendrías los datos del menú del día desde una fuente de datos (puede ser una base de datos, una API, etc.)
        // Este es un ejemplo simulado
        //return MenuTask;
        TODO("Por implementar")

    }
}