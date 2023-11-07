package com.integrame.app.login.data.model

import com.integrame.app.core.data.model.content.ContentProfile

data class StudentAuthProfile(
    val identityCard: IdentityCard,
    val contentProfile: ContentProfile,
    val authMethod: AuthMethod
)
