package com.integrame.app.login.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.core.util.MyResult
import com.integrame.app.login.data.model.IdentityCard
import com.integrame.app.login.data.repository.IdentityCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentLoginViewModel @Inject constructor(
    private val identityCardRepository: IdentityCardRepository
) : ViewModel() {
    var studentLoginUIState: StudentLoginUIState by mutableStateOf(StudentLoginUIState.Loading)
        private set

    init {
        viewModelScope.launch {
            loadIdentityCards()
        }
    }

    suspend fun loadIdentityCards() {
        viewModelScope.launch {
            when (val identityCards = identityCardRepository.getStudentsIdentityCards()) {
                is MyResult.Error -> TODO()
                is MyResult.Success -> studentLoginUIState = StudentLoginUIState.IdentitiesReady(identityCards.value)
            }
        }
    }
}

sealed interface StudentLoginUIState {
    object Loading : StudentLoginUIState
    data class IdentitiesReady(
        val identityCards: List<IdentityCard>
    ) : StudentLoginUIState
    data class Error(
        val error: String
    ) : StudentLoginUIState
}
