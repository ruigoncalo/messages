package com.ruigoncalo.data.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "users")
data class UserCached(
        @PrimaryKey val id: Long,
        val name: String,
        val avatarId: String)