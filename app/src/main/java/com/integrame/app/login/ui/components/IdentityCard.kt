package com.integrame.app.login.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.RemoteImage
import com.integrame.app.core.ui.components.DynamicImage
import com.integrame.app.login.data.model.IdentityCard
import com.integrame.app.ui.theme.IntegraMeTheme

@Composable
fun IdentityCard(
    identityCard: IdentityCard,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(192.dp)
            .clickable(onClick = onCardClick)
            .border(
                width = 12.dp,
                brush = SolidColor(MaterialTheme.colorScheme.primaryContainer),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(all = 12.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .semantics(mergeDescendants = true) {},
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DynamicImage(
            image = identityCard.avatar,
            modifier = Modifier
                .padding(top = 12.dp)
                .weight(1f)
                .clip(CircleShape)
        )

        Text(
            text = identityCard.nickname,
            style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center),
            modifier = Modifier.padding(vertical = 12.dp),
            maxLines = 2
        )
    }
}

@Preview
@Composable
fun IdentityCardPreview() {
    IntegraMeTheme {
        IdentityCard(
            IdentityCard(
                userId = 0,
                nickname = "Ismale Cpy tiene un nombre",
                avatar = RemoteImage(
                    imageUrl = "https://imgs.search.brave.com/cltbF8SKHppSqlmt9AYkhME57dJ3663AtJNlbWZv0Iw/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9zdDIu/ZGVwb3NpdHBob3Rv/cy5jb20vMTIwNzk5/OS82MjcxL2kvNjAw/L2RlcG9zaXRwaG90/b3NfNjI3MTY0OTEt/c3RvY2stcGhvdG8t/Y29ja2VyZWwtYXZh/dGFyLmpwZw",
                    id = 0,
                    altDescription = "Avatar"
                )
            ),
            {}
        )
    }

}