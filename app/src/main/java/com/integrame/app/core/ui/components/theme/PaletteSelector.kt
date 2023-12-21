package com.integrame.app.core.ui.components.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.integrame.app.ui.theme.palettes.Palette


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PaletteSelector(
    paletteList: List<Palette>,
    onClickPalette: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement =
        Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        paletteList.forEachIndexed { i, palette ->
            Column(
                modifier = Modifier.clickable { onClickPalette(i) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(color = palette.displayColor)
                )

                Text(
                    text = palette.displayName,
                    modifier = Modifier.padding(top = 8.dp),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
