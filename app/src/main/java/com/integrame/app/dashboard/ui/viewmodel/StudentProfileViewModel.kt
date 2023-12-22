package com.integrame.app.dashboard.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.core.data.repository.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentProfileViewModel @Inject constructor(
    private val themeRepository: ThemeRepository
): ViewModel() {
    fun changeThemePalette(paletteId: Int) {
        viewModelScope.launch {
            themeRepository.setPaletteId(paletteId)
        }
    }
}