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