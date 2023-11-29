package com.integrame.app.login.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.integrame.app.core.ui.components.ErrorCard
import com.integrame.app.core.ui.components.appbar.PaginatedBottomAppBar
import com.integrame.app.login.data.model.IdentityCard
import com.integrame.app.login.ui.components.IdentityCard
import com.integrame.app.login.ui.viewmodel.StudentLoginUIState
import com.integrame.app.login.ui.viewmodel.StudentLoginViewModel
import com.integrame.app.ui.theme.IntegraMeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentLoginScreen(
    onIdentitySelected: (Int) -> Unit,
    onTeacherLoginSelected: () -> Unit,
    modifier: Modifier = Modifier,
    studentLoginViewModel: StudentLoginViewModel = hiltViewModel()
) {
    val loginUIState = studentLoginViewModel.studentLoginUIState

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Identificación Alumno",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Buscador",
                            modifier = Modifier.size(32.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = onTeacherLoginSelected,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.School,
                            contentDescription = "Identificación Profesor",
                            modifier = Modifier.size(32.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
        bottomBar = {
            if (loginUIState is StudentLoginUIState.IdentitiesReady) {
                val currentPage = studentLoginViewModel.currentPage
                val totalPages = studentLoginViewModel.getTotalPages()
                PaginatedBottomAppBar(
                    currentPage = currentPage + 1,
                    isFirstPage = currentPage == 0,
                    isLastPage = currentPage == totalPages - 1,
                    onPressPrevious = { studentLoginViewModel.previousPage() },
                    onPressNext = { studentLoginViewModel.nextPage() },
                    modifier = Modifier.height(65.dp),
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        when (loginUIState) {
            is StudentLoginUIState.IdentitiesReady -> {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    ) {
                    IdentityCardGrid(
                        studentLoginViewModel.getIdentityCardsPage(),
                        onIdentityCardClick = onIdentitySelected,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .semantics { testTag = "Tarjeta alumno" }
                    )
                }
            }
            is StudentLoginUIState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier
                        .align(Alignment.Center)
                        .size(80.dp))
                }
            }
            is StudentLoginUIState.Error -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    ErrorCard(
                        errorDescription = loginUIState.error,
                        errorButtonText = "Cerrar",
                        errorButtonImage = {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Cerrar",
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        },
                        onPressContinue = { studentLoginViewModel.reloadIdentityCards() },
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
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
        modifier = modifier,
        userScrollEnabled = false
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
