package com.integrame.app.login.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.login.data.model.IdentityCard
import com.integrame.app.login.ui.components.IdentityCard
import com.integrame.app.ui.theme.IntegraMeTheme

@Composable
fun StudentLoginScreen(
    onIdentitySelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    IdentityCardGrid(
        List(15) {_ -> IdentityCard(
            userID = 0,
            nickname = "Ismale Cpy tiene un nombre",
            avatar = ImageContent(
                imageUrl = "https://imgs.search.brave.com/cltbF8SKHppSqlmt9AYkhME57dJ3663AtJNlbWZv0Iw/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9zdDIu/ZGVwb3NpdHBob3Rv/cy5jb20vMTIwNzk5/OS82MjcxL2kvNjAw/L2RlcG9zaXRwaG90/b3NfNjI3MTY0OTEt/c3RvY2stcGhvdG8t/Y29ja2VyZWwtYXZh/dGFyLmpwZw",
                id = 0,
                altDescription = "Avatar"
            )
        )},
        onIdentityCardClick = onIdentitySelected,
        modifier = Modifier.fillMaxSize()
    )
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
        items(identityCardList) { identityCard ->
            IdentityCard(
                identityCard = identityCard,
                onCardClick = { onIdentityCardClick(identityCard.userID) })
        }
    }
}

@Preview
@Composable
fun PreviewStudentLogin() {
    IntegraMeTheme {
        StudentLoginScreen(
            onIdentitySelected = {}
        )
    }
}
