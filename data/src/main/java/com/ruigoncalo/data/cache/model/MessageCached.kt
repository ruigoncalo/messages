package com.ruigoncalo.data.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters

@Entity(tableName = "messages")
data class MessageCached(
        @PrimaryKey val id: Long,
        val userId: Long,
        val content: String,
        @TypeConverters(ListTypeConverter::class)
        val attachments: List<AttachmentCached>)