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

import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.core.domain.repository.StudentRepository
import com.integrame.app.core.domain.repository.TeacherRepository
import com.integrame.app.core.domain.usecase.GetProfileUseCase
import com.integrame.app.login.domain.usecase.SignOutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetProfileUseCase(
        sessionRepository: SessionRepository,
        studentRepository: StudentRepository,
        teacherRepository: TeacherRepository
    ): GetProfileUseCase {
        return GetProfileUseCase(sessionRepository, studentRepository, teacherRepository)
    }

    @Provides
    @Singleton
    fun provideLogOutUseCase(
        sessionRepository: SessionRepository
    ): SignOutUseCase {
        return SignOutUseCase(sessionRepository)
    }
}
