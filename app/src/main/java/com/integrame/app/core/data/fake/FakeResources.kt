package com.integrame.app.core.data.fake

import com.integrame.app.core.data.model.content.AudioContent
import com.integrame.app.core.data.model.content.ContentAdaptationFormats
import com.integrame.app.core.data.model.content.ContentPack
import com.integrame.app.core.data.model.content.InteractionMethods
import com.integrame.app.core.data.model.content.RemoteImage
import com.integrame.app.core.data.model.content.TextContent
import com.integrame.app.core.data.model.content.VideoContent
import com.integrame.app.core.data.model.user.StudentProfile
import com.integrame.app.core.data.network.NetworkContentProfile
import com.integrame.app.core.data.network.NetworkImageContent
import com.integrame.app.core.data.network.toContentProfile
import com.integrame.app.login.data.model.ImageAuthMethod
import com.integrame.app.login.data.model.TextAuthMethod
import com.integrame.app.login.data.network.NetworkIdentityCard
import com.integrame.app.login.data.network.NetworkImageAuthMethod
import com.integrame.app.login.data.network.NetworkTextAuthMethod
import com.integrame.app.tasks.data.model.ClassroomMenuTask
import com.integrame.app.tasks.data.model.GenericTask
import com.integrame.app.tasks.data.model.GenericTaskStep
import com.integrame.app.tasks.data.model.Material
import com.integrame.app.tasks.data.model.MaterialRequest
import com.integrame.app.tasks.data.model.MaterialTask
import com.integrame.app.tasks.data.model.MenuOption
import com.integrame.app.tasks.data.model.MenuTask
import com.integrame.app.tasks.data.model.Task
import com.integrame.app.tasks.data.model.TaskCard
import com.integrame.app.tasks.data.model.TaskState
import com.integrame.app.tasks.data.model.TaskType

val NUM_STUDENTS = 15
val NUM_TASKS = 10

object FakeResources {
    val remoteImages = listOf(
        RemoteImage(
            imageUrl = "https://imgs.search.brave.com/nPvo3y2Adg-zq-UJjMmuz8edYP140xQI84q0UYb3a3s/rs:fit:860:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvY29v/bC1wcm9maWxlLXBp/Y3R1cmUtcWVqN2oy/ZWt1b3I5M3NzNy5q/cGc",
            0,
            "Carita sonriente"
        ),
        RemoteImage(
            imageUrl = "https://imgs.search.brave.com/abCMM68f1hRz7EEvblDhUKkaQTNZARl-OrH9r_q1ksM/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9pbWFn/ZXMudW5zcGxhc2gu/Y29tL3Jlc2VydmUv/TEpJWmx6SGdRN1dQ/U2g1S1ZUQ0JfVHlw/ZXdyaXRlci5qcGc_/YXV0bz1mb3JtYXQm/Zml0PWNyb3AmcT04/MCZ3PTEwMDAmaXhs/aWI9cmItNC4wLjMm/aXhpZD1NM3d4TWpB/M2ZEQjhNSHh6WldG/eVkyaDhNVFo4ZkhK/aGJtUnZiU1V5TUc5/aWFtVmpkSE44Wlc1/OE1IeDhNSHg4ZkRB/PQ",
            1,
            "Ola de colores"
        ),
        RemoteImage(
            imageUrl = "https://imgs.search.brave.com/ySAng48BmqRRrIqyQoTihTSiorhICEYNMMcL7ThLI2A/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9pbWcu/ZnJlZXBpay5jb20v/ZnJlZS1waG90by9t/eXN0ZXJpb3VzLWds/b3dpbmctZ2FsYXh5/LWNyZWF0ZXMtZGVl/cC1ibHVlLWJhY2tk/cm9wLWdlbmVyYXRl/ZC1ieS1haV8xODg1/NDQtOTU3Ny5qcGc_/c2l6ZT02MjYmZXh0/PWpwZw",
            2,
            "Máquina de escribir"
        ),
        RemoteImage(
            imageUrl = "https://imgs.search.brave.com/FacTMTyNHPKLp02C6oT8c-JmM70hAYetdoWY0b4eAgI/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9ncmF0/aXNvZ3JhcGh5LmNv/bS93cC1jb250ZW50/L3VwbG9hZHMvMjAy/My8wNi9ncmF0aXNv/Z3JhcGh5LXdhdGVy/bWVsb24tcG9wc2lj/bGUtZnJlZS1zdG9j/ay1waG90by04MDB4/NTI1LmpwZw",
            3,
            "Palito de sandía"
        )
    )

    val taskCards: List<TaskCard> = listOf(
        TaskCard(
            0,
            TaskState.Failed,
            "Hacer la cama",
            remoteImages[0],
            TaskType.Generic
        ),
        TaskCard(
            1,
            TaskState.Pending,
            "Menú comedor pero tiene un nombre extremadamente largo",
            remoteImages[1],
            TaskType.FoodMenu
        ),
        TaskCard(
            2,
            TaskState.Completed,
            "Petición almacén",
            remoteImages[2],
            TaskType.MaterialRequest
        ),
        TaskCard(
            3,
            TaskState.Completed,
            "Petición almacén",
            remoteImages[3],
            TaskType.MaterialRequest
        )
    )

    val contentProfiles = List(NUM_STUDENTS) { i ->
        NetworkContentProfile(
            contentAdaptationFormats = listOf(ContentAdaptationFormats.Text, ContentAdaptationFormats.Audio, ContentAdaptationFormats.Video, ContentAdaptationFormats.Image),
            interactionMethods = listOf(InteractionMethods.Default)
        )
    }

    val menuTasks: List<MenuTask> = List(NUM_TASKS) { i ->
        MenuTask(
            "Tarea Menú $i",
            remoteImages[i % remoteImages.size],
            List(minOf(4, i + 1)) { j ->
                ClassroomMenuTask(
                    j,
                    List(j+1){ k ->
                        MenuOption(
                            "Menú $j-$k",
                            remoteImages[k % remoteImages.size]
                        )
                    }
                )
            }
        )
    }

    val genericTasks: List<GenericTask> = List(NUM_TASKS) { i ->
        GenericTask(
            "Tarea Genérica $i",
            remoteImages[i % remoteImages.size],
            List(minOf(4, i + 1)) { j ->
                GenericTaskStep(
                    "Paso $j",
                    false,
                    ContentPack(
                        TextContent("Texto $j"),
                        remoteImages[j % remoteImages.size],
                        VideoContent("", -1),
                        AudioContent("", -1)
                    )
                )
            },
            remoteImages[i % remoteImages.size],
            false
        )
    }

    val materialTasks: List<MaterialTask> = List(NUM_TASKS) { i ->
        MaterialTask(
            "Tarea Material $i",
            remoteImages[i % remoteImages.size],
            List(minOf(4, i + 1)) { j ->
                MaterialRequest(
                    Material(
                        remoteImages[j % remoteImages.size],
                        properties = emptyList()
                    ),
                    j,
                    remoteImages[j % remoteImages.size],
                    false
                )
            }
        )
    }

    val studentProfiles: List<StudentProfile>
    val tasks: List<Task>

    init {
        studentProfiles = List(NUM_STUDENTS) { i ->
            StudentProfile(
                userId = i,
                name = "Estudiante $i",
                surnames = "S. G.",
                nickname = "Nick_$i",
                avatar = remoteImages[i % remoteImages.size],
                contentProfiles[i].toContentProfile()
            )
        }

        tasks = listOf(
            genericTasks[0],
            menuTasks[0],
            materialTasks[0],
            materialTasks[1]
        )
    }

    val identityCardList = List(NUM_STUDENTS) { i ->
        NetworkIdentityCard(
            userId = i,
            nickname = "Nick Alumno Número $i",
            avatar = NetworkImageContent(
                id = i,
                altDescription = "Avatar Gallina",
                imageUrl = remoteImages[i % remoteImages.size].imageUrl
            )
        )
    }

    val authMethodList = List(NUM_STUDENTS) { i ->
        if (i % 2 == 0)
            TextAuthMethod
        else
            ImageAuthMethod(steps = 3, List(4) { j -> remoteImages[j % remoteImages.size] })
    }
}
