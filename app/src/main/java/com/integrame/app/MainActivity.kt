package com.integrame.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.tasks.ui.screens.MenuTaskScreen
import com.integrame.app.ui.IntegraMeApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntegraMeApp()
            //MenuTaskScreen(task = FakeResources.menuTasks[0], onNavigateBack = { /*TODO*/ }, onPressHome = { /*TODO*/ }) { }
        }
    }
}
