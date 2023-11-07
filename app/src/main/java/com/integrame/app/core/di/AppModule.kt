package com.integrame.app.core.di

import android.content.Context
import com.integrame.app.core.data.local.IntegraMeDatabase
import com.integrame.app.core.data.network.FakeIntegraMeApi
import com.integrame.app.core.data.network.IntegraMeApi
import com.integrame.app.core.data.repository.SessionRepositoryImpl
import com.integrame.app.core.data.repository.StudentRespositoryImpl
import com.integrame.app.login.data.repository.AuthRepositoryImpl
import com.integrame.app.login.data.repository.IdentityCardRepositoryImpl
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
        // TODO: Esto es temporal
        return FakeIntegraMeApi
        
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl("localhost/api/v1")
            .build()
            .create(IntegraMeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStudentRepositoryImpl(
        @ApplicationContext appContext: Context,
        api: IntegraMeApi
    ) : StudentRespositoryImpl {
        return StudentRespositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideSessionRepositoryImpl(@ApplicationContext appContext: Context) : SessionRepositoryImpl {
        return SessionRepositoryImpl(appContext)
    }

    @Provides
    @Singleton
    fun provideIdentityCardRepositoryImpl(api: IntegraMeApi) : IdentityCardRepositoryImpl {
        return IdentityCardRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideAuthRepostioryImpl(sessionRepository: SessionRepositoryImpl) : AuthRepositoryImpl {
        return AuthRepositoryImpl(sessionRepository)
    }
}
