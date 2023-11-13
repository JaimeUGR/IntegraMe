package com.integrame.app.core.data.fake

import com.integrame.app.core.data.model.content.ContentAdaptationFormats
import com.integrame.app.core.data.model.content.InteractionMethods
import com.integrame.app.core.data.model.content.RemoteImage
import com.integrame.app.core.data.model.user.StudentProfile
import com.integrame.app.core.data.network.NetworkContentProfile
import com.integrame.app.core.data.network.NetworkImageContent
import com.integrame.app.core.data.network.toContentProfile
import com.integrame.app.login.data.network.NetworkIdentityCard
import com.integrame.app.login.data.network.NetworkImageAuthMethod
import com.integrame.app.login.data.network.NetworkTextAuthMethod

val NUM_STUDENTS = 15

object FakeResources {
    val networkImages: List<NetworkImageContent> = listOf(
        NetworkImageContent(0, "ASD"),
        NetworkImageContent(1, "BSD"),
        NetworkImageContent(2, "BSD"),
        NetworkImageContent(3, "BSD")
    )

    val contentProfiles = List(NUM_STUDENTS) { i->
        NetworkContentProfile(
            contentAdaptationFormats = listOf(ContentAdaptationFormats.Image),
            interactionMethods = listOf(InteractionMethods.Default)
        )
    }

    val studentProfiles: List<StudentProfile>

    init {
        studentProfiles = List(NUM_STUDENTS) { i ->
            StudentProfile(
                userId = i,
                name = "Estudiante $i",
                surnames = "S. G.",
                nickname = "Nick_$i",
                avatar = RemoteImage(
                    imageUrl = "https://imgs.search.brave.com/nPvo3y2Adg-zq-UJjMmuz8edYP140xQI84q0UYb3a3s/rs:fit:860:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvY29v/bC1wcm9maWxlLXBp/Y3R1cmUtcWVqN2oy/ZWt1b3I5M3NzNy5q/cGc",
                    -1,
                    "Carita sonriente"
                ),
                contentProfiles[i].toContentProfile()
            )
        }
    }

    val identityCardList = List(NUM_STUDENTS) { i -> NetworkIdentityCard(
        userId = i,
        nickname = "Nick Alumno Número $i",
        avatar = NetworkImageContent(
            id = i,
            altDescription = "Avatar Gallina"
        )
    )
    }

    val authMethodList = List(NUM_STUDENTS) { i ->
        if (i%2 == 0)
            NetworkTextAuthMethod
        else
            NetworkImageAuthMethod(steps = 3, images = networkImages)
    }
}