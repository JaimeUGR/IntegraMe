package com.integrame.app.core.di

import android.content.Context
import com.integrame.app.core.data.local.IntegraMeDatabase
import com.integrame.app.core.data.network.IntegraMeApi
import com.integrame.app.core.data.repository.SessionRepositoryImpl
import com.integrame.app.login.data.repository.AuthRepositoryImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : IntegraMeDatabase {
        return IntegraMeDatabase.getDatabase(appContext)
    }

    @Provides
    @Singleton
    fun provideIntegraMeApi() : IntegraMeApi {
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl("localhost/api/v1")
            .build()
            .create(IntegraMeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSessionRepository(@ApplicationContext appContext: Context) : SessionRepositoryImpl {
        return SessionRepositoryImpl(appContext)
    }

    @Provides
    @Singleton
    fun provideAuthRepostiory(sessionRepository: SessionRepositoryImpl) : AuthRepositoryImpl {
        return AuthRepositoryImpl(sessionRepository)
    }
}
