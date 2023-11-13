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
