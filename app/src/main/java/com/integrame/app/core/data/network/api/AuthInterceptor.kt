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
