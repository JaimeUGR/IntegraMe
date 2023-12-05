package com.integrame.app.core.di

import android.content.Context
import com.integrame.app.core.data.local.IntegraMeDatabase
import com.integrame.app.core.data.network.api.AuthInterceptor
import com.integrame.app.core.data.network.api.FakeIntegraMeApi
import com.integrame.app.core.data.network.api.IntegraMeApi
import com.integrame.app.core.data.repository.SessionRepositoryImpl
import com.integrame.app.core.data.repository.StudentRespositoryImpl
import com.integrame.app.core.data.repository.TeacherRepositoryImpl
import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.login.data.repository.AuthRepositoryImpl
import com.integrame.app.login.data.repository.IdentityCardRepositoryImpl
import com.integrame.app.tasks.data.repository.GenericTaskRepositoryImpl
import com.integrame.app.tasks.data.repository.MaterialTaskRepositoryImpl
import com.integrame.app.tasks.data.repository.TaskRepositoryImpl
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
        return FakeIntegraMeApi
        
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl("http://35.210.189.6:6969/api/v1/")
            .client(OkHttpClient.Builder().addInterceptor(AuthInterceptor(sessionRepository)).build())
            .build()
            .create(IntegraMeApi::class.java)
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
        return GenericTaskRepositoryImpl()
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
