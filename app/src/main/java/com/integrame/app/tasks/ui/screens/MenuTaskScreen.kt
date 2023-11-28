package com.integrame.app.tasks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.ui.components.appbar.StudentTaskTopAppBar
import com.integrame.app.tasks.data.model.MenuTaskModel
import com.integrame.app.tasks.ui.viewmodel.SelectClassMenuViewModel
import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.data.network.toContentProfile
import com.integrame.app.core.ui.components.DynamicImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuTaskScreen(
    taskModel: MenuTaskModel,
    contentProfile: ContentProfile,
    onNavigateBack: () -> Unit,
    onPressHome: () -> Unit,
    onPressChat: () -> Unit,
    modifier: Modifier = Modifier,
    selectClassMenuViewModel: SelectClassMenuViewModel = hiltViewModel()
) {

    var numClassroom = 0
    var padding = 10.dp

    // Observar los cambios en el estado del ViewModel
    val menuTaskTaskUIState = selectClassMenuViewModel.uiState

    LaunchedEffect(Unit) {
        selectClassMenuViewModel.loadClassrooms(taskModel)
    }

    Scaffold(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer),

        topBar = {
            StudentTaskTopAppBar(
                title = taskModel.displayName,
                onNavigateBack = onNavigateBack,
                onPressHome = onPressHome,
                onPressChat = onPressChat
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
        ) {
            itemsIndexed(selectClassMenuViewModel.classrooms) { index, item ->
                Button(
                    modifier = Modifier
                        .height(130.dp)
                        .padding(all = 10.dp),
                    onClick = {
                        numClassroom = index;
                    },
                    shape = RoundedCornerShape(26.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .height(75.dp)
                            .padding(all = padding),
                        text = "La clase $numClassroom",
                        fontSize = MaterialTheme.typography.displaySmall.fontSize
                    )
                    DynamicImage(
                        image = FakeResources.remoteImages[0],
                        modifier = Modifier
                            .size(100.dp)
                            .padding(all = padding)
                    )
                }
            }
        }
    }


}

@Preview(showSystemUi = true)
@Composable
fun MenuTaskScreenPreview() {
    MenuTaskScreen(
        taskModel = MenuTaskModel.fromMenuTask(FakeResources.menuTasks[0]),
        contentProfile = FakeResources.contentProfiles[0].toContentProfile(),
        onNavigateBack = { /*TODO*/ },
        onPressHome = { /*TODO*/ },
        onPressChat = { /*TODO*/ },
        modifier = Modifier.fillMaxSize()
    )
}

