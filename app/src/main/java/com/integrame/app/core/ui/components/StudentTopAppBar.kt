package com.integrame.app.core.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.integrame.app.core.data.model.content.ContentProfile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentTopAppBar(
    contentProfile: ContentProfile,
    title: String,
    modifier: Modifier = Modifier
) {
    TopAppBar(title = { /*TODO*/ })
}