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

package com.integrame.app.core.di

import com.integrame.app.core.data.repository.SessionRepositoryImpl
import com.integrame.app.core.data.repository.StudentRespositoryImpl
import com.integrame.app.core.data.repository.TeacherRepositoryImpl
import com.integrame.app.core.data.repository.ThemeRepository
import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.core.domain.repository.StudentRepository
import com.integrame.app.core.domain.repository.TeacherRepository
import com.integrame.app.login.data.repository.AuthRepositoryImpl
import com.integrame.app.login.data.repository.IdentityCardRepositoryImpl
import com.integrame.app.login.domain.repository.AuthRepository
import com.integrame.app.login.domain.repository.IdentityCardRepository
import com.integrame.app.tasks.data.repository.GenericTaskRepositoryImpl
import com.integrame.app.tasks.data.repository.MaterialTaskRepositoryImpl
import com.integrame.app.tasks.data.repository.TaskRepositoryImpl
import com.integrame.app.tasks.domain.repository.GenericTaskRepository
import com.integrame.app.tasks.domain.repository.MaterialTaskRepository
import com.integrame.app.tasks.data.repository.MenuTaskRepositoryImpl
import com.integrame.app.tasks.domain.repository.MenuTaskRepository
import com.integrame.app.tasks.domain.repository.TaskRepository
import com.integrame.app.teacher.data.repository.TeacherTaskRepositoryImpl
import com.integrame.app.teacher.domain.repository.TeacherTaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindStudentRepository(
        studentRespositoryImpl: StudentRespositoryImpl
    ): StudentRepository

    @Binds
    @Singleton
    abstract fun bindTaskRepository(
        taskRepositoryImpl: TaskRepositoryImpl
    ): TaskRepository

    @Binds
    @Singleton
    abstract fun bindGenericTaskRepository(
        genericTaskRepositoryImpl: GenericTaskRepositoryImpl
    ): GenericTaskRepository

    @Binds
    @Singleton
    abstract fun bindMenuTaskRepository(
        menuTaskRepositoryImpl: MenuTaskRepositoryImpl
    ): MenuTaskRepository
  
    @Binds
    @Singleton
    abstract fun bindMaterialTaskRepository(
        materialTaskRepositoryImpl: MaterialTaskRepositoryImpl
    ): MaterialTaskRepository

    @Binds
    @Singleton
    abstract fun bindTeacherRepository(
        teacherRepositoryImpl: TeacherRepositoryImpl
    ): TeacherRepository

    @Binds
    @Singleton
    abstract fun bindTeacherTaskRepository(
        teacherTaskRepositoryImpl: TeacherTaskRepositoryImpl
    ): TeacherTaskRepository

    @Binds
    @Singleton
    abstract fun bindSessionRepository(
        sessionRepositoryImpl: SessionRepositoryImpl
    ): SessionRepository

    @Binds
    @Singleton
    abstract fun bindIdentityCardRepository(
        identityCardRepositoryImpl: IdentityCardRepositoryImpl
    ): IdentityCardRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}
