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

package com.integrame.app.login.domain.repository

import com.integrame.app.core.util.RequestResult
import com.integrame.app.login.data.model.IdentityCard

interface IdentityCardRepository {
    suspend fun getStudentsIdentityCards() : RequestResult<List<IdentityCard>>
    suspend fun getIdentityCard(userId: Int) : RequestResult<IdentityCard>
}
