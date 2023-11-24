package com.integrame.app.tasks.ui.viewmodel

<<<<<<< HEAD
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.login.domain.repository.AuthRepository
import com.integrame.app.tasks.data.model.MenuTask
import com.integrame.app.tasks.data.repository.TaskRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuTaskViewModel @Inject constructor(
    private val menuTaskRepository : TaskRepositoryImpl

) : ViewModel() {
    //Estado del ViewModel
    private val _menutaskState = mutableStateOf(MenuTaskUIState.Loading)
    val menutaskState: State<MenuTaskUIState> get() = _menutaskState

    //Datos de la tarea de comedor
    private var menuTask: MenuTask? = null

    //Índice actual de la comida seleccionada
    private var selectedFoodIndex = 0


    //Inicialización del ViewModel
    init{

    }

    //Carga de configuración inicial de la tarea
    private fun loadInitialMenuTask(){
        //Simular carga de datos iniciales
    }

    //Cargar tarea de comedor específica
    suspend fun loadMenuTask(taskId: Int){
        viewModelScope.launch {
            try {
                val menuTask = menuTaskRepository.getMenuTask(taskId)
                //onMenuTaskLoaded(menuTask)
            }catch (e: Exception){
                //_menutaskState.value = MenuTaskUIState.Error(e.message ?: "Error")

            }
        }
    }

    //Acción cuando se carga una tarea de comedor
    private suspend fun onMenuTaskLoaded(menuTask: MenuTask){
        this.menuTask = menuTask
        //Actualizar el estado del ViewModel con los datos de la tarea del comedor
        //_menutaskState.value = MenuTaskUIState.Loaded(menuTask)
    }

    //Acciones para cambiar la comida seleccionada
    fun nextFood(){
        selectedFoodIndex = (selectedFoodIndex + 1) % (menuTask?.classroomMenus?.get(0)?.menuOptions?.size ?: 1)
        //updateComedorState
    }

    fun previousFood(){
        selectedFoodIndex = if (selectedFoodIndex > 0) selectedFoodIndex -1 else menuTask?.classroomMenus?.get(0)?.menuOptions?.size?.minus(1) ?: 0
        //updateComedorState
    }

    // Acción para cambiar la cantidad de comida seleccionada
    fun changeFoodQuantity(quantity : Int){

    }

    // Actualizar el esstado del ViewModel después de cambiar la comida seleccionada
    private fun updateMenuTaskState(){
        // Actualizar el estao del ViewModel con los nuevos datos
        //_menutaskState.value = MenuTaskUIState.Loaded(menuTask, selectedFoodIndex)
    }


}


// Estado del UI del MenuTaskViewModel
sealed class MenuTaskUIState {
    object Loading : MenuTaskUIState()
    data class Loaded(val menuTask: MenuTask, val selectedFoodIndex: Int = 0) : MenuTaskUIState()
    data class Error(val errorMessage: String) : MenuTaskUIState()
}



=======
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.tasks.data.model.MenuTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
/*

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

 */
>>>>>>> adced9643fedba8407d43913dde986ee9eaf5a7f
