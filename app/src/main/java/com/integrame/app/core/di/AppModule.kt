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

import android.content.Context
import com.integrame.app.core.data.local.IntegraMeDatabase
import com.integrame.app.core.data.network.api.AuthInterceptor
import com.integrame.app.core.data.network.api.FakeIntegraMeApi
import com.integrame.app.core.data.network.api.IntegraMeApi
import com.integrame.app.core.data.repository.SessionRepositoryImpl
import com.integrame.app.core.data.repository.StudentRespositoryImpl
import com.integrame.app.core.data.repository.TeacherRepositoryImpl
import com.integrame.app.core.data.repository.ThemeRepository
import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.login.data.repository.AuthRepositoryImpl
import com.integrame.app.login.data.repository.IdentityCardRepositoryImpl
import com.integrame.app.tasks.data.repository.GenericTaskRepositoryImpl
import com.integrame.app.tasks.data.repository.MaterialTaskRepositoryImpl
import com.integrame.app.tasks.data.repository.MenuTaskRepositoryImpl
import com.integrame.app.tasks.data.repository.TaskRepositoryImpl
import com.integrame.app.teacher.data.repository.TeacherTaskRepositoryImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): IntegraMeDatabase {
        return IntegraMeDatabase.getDatabase(appContext)
    }

    @Provides
    @Singleton
    fun provideIntegraMeApi(sessionRepository: SessionRepository): IntegraMeApi {
        // TODO: Integrar API
        //return FakeIntegraMeApi
        val json = Json { ignoreUnknownKeys = true}

        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl("http://34.175.9.11:6969/api/v1/")
            .client(OkHttpClient.Builder().addInterceptor(AuthInterceptor(sessionRepository)).build())
            .build()
            .create(IntegraMeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideThemeRepositoryImpl(
        @ApplicationContext appContext: Context
    ): ThemeRepository {
        return ThemeRepository(appContext)
    }

    @Provides
    @Singleton
    fun provideStudentRepositoryImpl(
        @ApplicationContext appContext: Context,
        api: IntegraMeApi
    ): StudentRespositoryImpl {
        return StudentRespositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideTaskRepositoryImpl(
        api: IntegraMeApi
    ): TaskRepositoryImpl {
        return TaskRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGenericTaskRepositoryImpl(api: IntegraMeApi): GenericTaskRepositoryImpl {
        return GenericTaskRepositoryImpl(api)
    }
    
    @Provides
    @Singleton
    fun provideMenuTaskRepositoryImpl(api: IntegraMeApi): MenuTaskRepositoryImpl {
        return MenuTaskRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideMaterialTaskRepositoryImpl(api: IntegraMeApi): MaterialTaskRepositoryImpl {
        return MaterialTaskRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideTeacherRepositoryImpl(api: IntegraMeApi): TeacherRepositoryImpl {
        return TeacherRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideTeacherTaskRepositoryImpl(api: IntegraMeApi, identityCardRepositoryImpl: IdentityCardRepositoryImpl): TeacherTaskRepositoryImpl {
        return TeacherTaskRepositoryImpl(api, identityCardRepositoryImpl)
    }



    @Provides
    @Singleton
    fun provideSessionRepositoryImpl(@ApplicationContext appContext: Context): SessionRepositoryImpl {
        return SessionRepositoryImpl(appContext)
    }

    @Provides
    @Singleton
    fun provideIdentityCardRepositoryImpl(api: IntegraMeApi): IdentityCardRepositoryImpl {
        return IdentityCardRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideAuthRepostioryImpl(
        sessionRepository: SessionRepositoryImpl,
        api: IntegraMeApi
    ): AuthRepositoryImpl {
        return AuthRepositoryImpl(sessionRepository, api)
    }
}
