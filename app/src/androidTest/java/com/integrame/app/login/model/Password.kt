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

package com.integrame.app.login.model

import com.integrame.app.login.data.model.ImagePassword
import com.integrame.app.login.data.model.StudentPassword
import kotlinx.serialization.json.Json
import com.integrame.app.login.data.model.TextPassword
import kotlinx.serialization.encodeToString
import org.junit.Test

/*class PasswordTests
{
    val textPassword = TextPassword("holiwi")
    val imagePassword = ImagePassword()

    init {
        imagePassword.addImage(2)
        imagePassword.addImage(5)
    }

    @Test
    fun checkSerialization()
    {
        println(Json.encodeToString(textPassword as StudentPassword))
        println(Json.encodeToString(imagePassword as StudentPassword))
    }
}*/