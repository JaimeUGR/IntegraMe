package com.integrame.app.teacher.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.integrame.app.teacher.domain.repository.TeacherTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MakeTaskScreenViewModel @Inject constructor(
    private val teacherTaskRepository: TeacherTaskRepository
) :ViewModel(){

}