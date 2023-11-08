package com.integrame.app.login.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.integrame.app.login.data.model.IdentityCard
import com.integrame.app.login.ui.components.IdentityCard
import com.integrame.app.login.ui.viewmodel.StudentLoginUIState
import com.integrame.app.login.ui.viewmodel.StudentLoginViewModel
import com.integrame.app.ui.theme.IntegraMeTheme

@Composable
fun StudentLoginScreen(
    onIdentitySelected: (Int) -> Unit,
    onTeacherLoginSelected: () -> Unit,
    modifier: Modifier = Modifier,
    studentLoginViewModel: StudentLoginViewModel = hiltViewModel()
) {
    when (val loginUIState = studentLoginViewModel.studentLoginUIState) {
        is StudentLoginUIState.IdentitiesReady -> {
            Column(modifier = modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    Text(
                        text = "Identificación Alumno",
                        modifier = Modifier.align(Alignment.Center).padding(vertical = 16.dp),
                        style = MaterialTheme.typography.titleLarge
                    )

                    IconButton(
                        onClick = onTeacherLoginSelected,
                        modifier = Modifier.align(Alignment.CenterEnd).padding(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.School,
                            contentDescription = "Identificación Profesor",
                            modifier = Modifier.size(40.dp),
                            tint = Color.Cyan
                        )
                    }
                }

                IdentityCardGrid(
                    loginUIState.identityCards,
                    onIdentityCardClick = onIdentitySelected,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        is StudentLoginUIState.Loading -> {
            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier
                    .align(Alignment.Center)
                    .size(80.dp))
            }
        }
        is StudentLoginUIState.Error -> TODO()
    }
}

@Composable
fun IdentityCardGrid(
    identityCardList: List<IdentityCard>,
    onIdentityCardClick: (Int) -> Unit,
    modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(24.dp),
        modifier = modifier
    ) {
        items(identityCardList, key = { identityCard ->
            identityCard.userId
        }) { identityCard ->
            IdentityCard(
                identityCard = identityCard,
                onCardClick = { onIdentityCardClick(identityCard.userId)}
            )
        }
    }
}

@Preview
@Composable
fun PreviewStudentLogin() {
    IntegraMeTheme {
        StudentLoginScreen(
            onIdentitySelected = {},
            onTeacherLoginSelected = {}
        )
    }
}
