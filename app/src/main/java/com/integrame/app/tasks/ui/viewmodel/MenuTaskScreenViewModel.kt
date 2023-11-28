package com.integrame.app.tasks.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.core.data.model.content.ContentPack
import com.integrame.app.tasks.data.model.GenericTaskStep
import com.integrame.app.tasks.data.model.MenuTask
import com.integrame.app.tasks.data.model.MenuTaskModel
import com.integrame.app.tasks.domain.repository.MenuTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val menuTaskRepository: MenuTaskRepository
): ViewModel() {
    private lateinit var menuTaskModel: MenuTaskModel





}

sealed interface MenuTaskUIState {
    object Loading: MenuTaskUIState



}

