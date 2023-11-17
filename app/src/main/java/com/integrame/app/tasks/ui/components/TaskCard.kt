package com.integrame.app.tasks.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.integrame.app.core.ui.components.DynamicImage
import com.integrame.app.tasks.data.model.TaskCard
import com.integrame.app.tasks.data.model.TaskState

@Composable
fun TaskCard(
    taskCard: TaskCard,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
            .clickable { onCardClick() }
            .border(3.dp, MaterialTheme.colorScheme.secondary)
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {
                contentDescription = "Tarjeta tarea"
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        DynamicImage(
            image = taskCard.displayImage,
            modifier = Modifier.requiredSize(100.dp).border(2.dp, MaterialTheme.colorScheme.secondary)
                .zIndex(1f),
            contentScale = ContentScale.Crop
        )

        Text(
            text = taskCard.displayName,
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f).zIndex(1f),
            fontSize = 18.sp, // TODO: Cambiar por materialtheme
            overflow = TextOverflow.Ellipsis
        )

        Icon(
            imageVector = when (taskCard.taskState) {
                TaskState.Failed -> Icons.Filled.Circle
                TaskState.Pending -> Icons.Outlined.Circle
                TaskState.Completed -> Icons.Filled.CheckCircle
            },
            contentDescription = "Icono de estado " + when (taskCard.taskState) {
                TaskState.Failed -> "fallida"
                TaskState.Pending -> "pendiente"
                TaskState.Completed -> "completada"
            },
            modifier = Modifier
                .padding(start = 16.dp)
                .size(40.dp).zIndex(0f),
            tint = when (taskCard.taskState) {
                TaskState.Failed -> Color.Red
                TaskState.Pending -> MaterialTheme.colorScheme.secondary
                TaskState.Completed -> Color.Green
            }
        )
    }
}