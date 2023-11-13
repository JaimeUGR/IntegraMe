package com.integrame.app.core.data.network.api

import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.core.util.Option
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

/*
    Inyecta la sessión actual en las peticiones que requieran autenticación
 */
class AuthInterceptor constructor(
    private val sessionRepository: SessionRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        if (!originalRequest.header("Authorized").toBoolean())
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
