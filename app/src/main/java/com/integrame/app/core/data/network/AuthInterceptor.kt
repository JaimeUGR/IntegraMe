package com.integrame.app.core.data.network

import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.core.util.Option
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/*
    Quizás se utilizará en el futuro pero por motivos prácticos y de testing, se ha optado
    por la inclusión manual de los tokens de autenticación a través del caso de uso base
    AuthorizedUseCase, encargado de proporcionar la session a los repositorios que la necesitan.
 */
class AuthInterceptor @Inject constructor(
    private val sessionRepository: SessionRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        if (!originalRequest.header("AuthorizationRequired").toBoolean())
            return chain.proceed(originalRequest)

        val sessionOpt = runBlocking {
            sessionRepository.getSession()
        }

        if (sessionOpt is Option.Some)
        {
            val session = sessionOpt.value

            val newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer ${session.token}")
                .build()

            return chain.proceed(newRequest)
        }

        return Response.Builder()
            .code(401)
            .message("Error: Unauthorized")
            .request(chain.request())
            .build()
    }
}
