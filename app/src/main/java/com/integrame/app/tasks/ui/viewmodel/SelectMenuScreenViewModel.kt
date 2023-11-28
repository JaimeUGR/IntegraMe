package com.integrame.app.tasks.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.integrame.app.tasks.data.model.MenuTaskModel
import com.integrame.app.tasks.domain.repository.MenuTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

