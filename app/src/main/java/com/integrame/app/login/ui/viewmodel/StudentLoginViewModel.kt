/*
 *
 * Copyright (c) IntegraMe - Champions 2024 - All rights reserved
 *
 *  You may use, distribute and modify this code under the terms of the
 *  Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0) license.
 *
 *  You should have received a copy of the CC BY-NC 4.0 license. Otherwise, read
 *  the full license text here: https://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 *  This file belongs to the project IntegraMe: https://github.com/JaimeUGR/IntegraMe
 */

package com.integrame.app.login.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.core.util.RequestResult
import com.integrame.app.login.data.model.IdentityCard
import com.integrame.app.login.domain.repository.IdentityCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentLoginViewModel @Inject constructor(
    private val identityCardRepository: IdentityCardRepository
) : ViewModel() {
    var studentLoginUIState: StudentLoginUIState by mutableStateOf(StudentLoginUIState.Loading)
        private set

    private val pageSize = 6
    var currentPage: Int by mutableIntStateOf(0)

    init {
        viewModelScope.launch {
            loadIdentityCards()
        }
    }

    fun nextPage() {
        currentPage++
    }

    fun previousPage() {
        currentPage--
    }

    fun getTotalPages(): Int {
        if (studentLoginUIState !is StudentLoginUIState.IdentitiesReady)
            return 0;

        return ((studentLoginUIState as StudentLoginUIState.IdentitiesReady).identityCards.size + pageSize - 1) / pageSize
    }

    fun getIdentityCardsPage(): List<IdentityCard> {
        if (studentLoginUIState !is StudentLoginUIState.IdentitiesReady)
            return emptyList()

        val identityCards = (studentLoginUIState as StudentLoginUIState.IdentitiesReady).identityCards

        return identityCards.subList(
            currentPage * pageSize,
            minOf((currentPage + 1) * pageSize, identityCards.size)
        )
    }

    fun reloadIdentityCards() {
        if (studentLoginUIState == StudentLoginUIState.Loading)
            return

        viewModelScope.launch {
            loadIdentityCards()
        }
    }

    private suspend fun loadIdentityCards() {
        studentLoginUIState = StudentLoginUIState.Loading
        studentLoginUIState = when (val requestResult = identityCardRepository.getStudentsIdentityCards()) {
            is RequestResult.Success -> StudentLoginUIState.IdentitiesReady(requestResult.data)
            is RequestResult.Error -> StudentLoginUIState.Error(requestResult.error)
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
