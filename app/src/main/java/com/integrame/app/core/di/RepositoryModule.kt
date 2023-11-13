package com.integrame.app.core.di

import com.integrame.app.core.data.repository.SessionRepositoryImpl
import com.integrame.app.core.data.repository.StudentRespositoryImpl
import com.integrame.app.core.data.repository.TeacherRepositoryImpl
import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.core.domain.repository.StudentRepository
import com.integrame.app.core.domain.repository.TeacherRepository
import com.integrame.app.login.data.repository.AuthRepositoryImpl
import com.integrame.app.login.data.repository.IdentityCardRepositoryImpl
import com.integrame.app.login.domain.repository.AuthRepository
import com.integrame.app.login.domain.repository.IdentityCardRepository
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
    abstract fun bindTeacherRepository(
        teacherRepositoryImpl: TeacherRepositoryImpl
    ): TeacherRepository

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
