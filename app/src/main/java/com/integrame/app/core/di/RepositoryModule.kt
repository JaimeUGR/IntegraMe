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
