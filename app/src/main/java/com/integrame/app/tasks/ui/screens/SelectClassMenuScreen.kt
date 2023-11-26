package com.integrame.app.tasks.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.integrame.app.R
import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.ui.components.appbar.StudentTaskTopAppBar
import com.integrame.app.tasks.data.model.MenuTask
import java.lang.System.exit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectClassMenuScreen(
    modifier: Modifier = Modifier,
    task: MenuTask,
    onNavigateBack: () -> Unit,
    onPressHome: () -> Unit,
    onPressChat: () -> Unit,
) {

    var numClaseActual by remember { mutableStateOf(0) }
    var padding = 10.dp


        Scaffold(
            modifier = modifier
                .background(MaterialTheme.colorScheme.primaryContainer),

            topBar = {
                StudentTaskTopAppBar(
                    title = task.displayName,
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
                itemsIndexed(task.classroomMenus) { index, item ->
                    Button(
                        modifier = Modifier
                            .height(130.dp)
                            .padding(all = 10.dp),
                        onClick = {
                            numClaseActual = index;
                        },
                        shape = RoundedCornerShape(26.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .height(75.dp)
                                .padding(all = padding),
                            text = "La clase X",
                            fontSize = MaterialTheme.typography.displaySmall.fontSize
                        )
                        Image(
                            painter = painterResource(id = R.drawable.clase),
                            contentDescription = "Imagen de una clase",
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
fun SelectClassMenuScreenPreview() {
    SelectClassMenuScreen(
        task = FakeResources.menuTasks[5],
        onNavigateBack = { /*TODO*/ },
        onPressHome = { /*TODO*/ },
        onPressChat = { /*TODO*/ },
        modifier = Modifier.fillMaxSize()
    )
}