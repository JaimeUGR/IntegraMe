package com.integrame.app.core.di

import com.integrame.app.core.data.repository.SessionRepositoryImpl
import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.login.data.repository.AuthRepositoryImpl
import com.integrame.app.login.domain.repository.AuthRepository
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
    abstract fun bindSessionRepository(
        sessionRepositoryImpl: SessionRepositoryImpl
    ) : SessionRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ) : AuthRepository
}
