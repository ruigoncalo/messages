package com.ruigoncalo.data.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "attachments")
data class AttachmentCached(
        @PrimaryKey val id: String,
        val title: String,
        val url: String,
        val thumbnailUrl: String)