package com.integrame.app.login.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.core.util.MyResult
import com.integrame.app.login.data.model.StudentAuthProfile
import com.integrame.app.login.data.model.StudentPassword
import com.integrame.app.login.domain.repository.AuthRepository
import com.integrame.app.login.domain.usecase.GetStudentAuthProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentAuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val getStudentAuthProfileUseCase: GetStudentAuthProfileUseCase
) : ViewModel() {
    var studentAuthUIState: StudentAuthUIState by mutableStateOf(StudentAuthUIState.Loading)
        private set

    fun loadStudentData(userId: Int) {
        viewModelScope.launch {
            studentAuthUIState = StudentAuthUIState.Loading // TODO: Puede que no sea necesario hacer este reset

            when (val studentProfile = getStudentAuthProfileUseCase(userId)) {
                is MyResult.Success -> {
                    studentAuthUIState = StudentAuthUIState.UserLoaded(studentProfile.value)
                }
                is MyResult.Error -> TODO()
            }
        }
    }

    fun textAuthSignIn() {

    }

    fun imageAuthSignIn() {

    }

    private fun signIn(userId: Int, password: StudentPassword) {

    }
}

sealed interface StudentAuthUIState {
    object Loading : StudentAuthUIState
    data class UserLoaded(
        val studentAuthProfile: StudentAuthProfile
    ) : StudentAuthUIState
    data class Error(
        val error: String
    ) : StudentAuthUIState
}
