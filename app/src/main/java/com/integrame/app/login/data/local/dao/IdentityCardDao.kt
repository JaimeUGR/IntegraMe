package com.integrame.app.login.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.integrame.app.login.data.model.IdentityCard

@Dao
interface IdentityCardDao {
    @Query("SELECT userID, name, surnames, avatarUrl FROM Students")
    fun getIdentityCards() : List<IdentityCard>
}