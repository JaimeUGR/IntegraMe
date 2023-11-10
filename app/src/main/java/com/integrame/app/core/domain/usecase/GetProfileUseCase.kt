package com.integrame.app.core.domain.usecase

import com.integrame.app.core.data.local.entities.UserType
import com.integrame.app.core.data.model.session.Session
import com.integrame.app.core.data.model.user.Profile
import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.core.domain.repository.StudentRepository
import com.integrame.app.core.domain.repository.TeacherRepository
import com.integrame.app.core.util.AuthRequestResult
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val studentRepository: StudentRepository,
    private val teacherRepository: TeacherRepository
): AuthenticatedUseCase<Profile>(sessionRepository) {
    override suspend fun onInvoke(session: Session): AuthRequestResult<Profile> {
        return when (session.userType) {
            UserType.Student -> {
                studentRepository.getProfile(session)
            }
            UserType.Teacher -> {
                teacherRepository.getProfile(session.userId)
            }
        }
    }
}
