package com.integrame.app.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.data.model.content.RemoteImage
import com.integrame.app.core.data.model.content.UserContentAdaptationFormats
import com.integrame.app.core.data.model.content.UserInteractionMethods
import com.integrame.app.core.data.model.user.StudentProfile
import com.integrame.app.ui.theme.IntegraMeTheme

// TODO: En el menú principal la barra tendrá el botón de cerrar sesión, título y el botón de campanita y perfil.
// En el resto de lugares, tendrá el botón de retroceder, título, icono lugar y botón de home.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentTopAppBar(
    studentProfile: StudentProfile,
    title: String,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        },
        modifier = modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Home, "")
            }
            IconButton(onClick = { /*TODO*/ }) {
                DynamicImage(
                    image = studentProfile.avatar,
                    modifier = Modifier.clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun PreviewStudentTopAppBar() {
    val studentProfile = StudentProfile(
        userId = 0,
        name = "Martín",
        surnames = "R. C.",
        nickname = "@Martinez",
        avatar = RemoteImage("https://imgs.search.brave.com/nPvo3y2Adg-zq-UJjMmuz8edYP140xQI84q0UYb3a3s/rs:fit:860:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJzLmNvbS9p/bWFnZXMvaGQvY29v/bC1wcm9maWxlLXBp/Y3R1cmUtcWVqN2oy/ZWt1b3I5M3NzNy5q/cGc"
            , 1, ""),
        contentProfile = ContentProfile(
            UserInteractionMethods(true, true, true, true, true, true),
            UserContentAdaptationFormats(true, true, true, true)
        )
    )

    IntegraMeTheme {
        Scaffold(
            topBar = {
                StudentTopAppBar(
                    studentProfile = studentProfile,
                    title = "Esta pantalla tiene un nombre muy largo"
                )
            }
        ) {
            Text(text = "HOLA", modifier = Modifier.padding(it))
        }
    }
}
