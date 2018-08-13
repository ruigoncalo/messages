package com.ruigoncalo.data.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.ruigoncalo.data.cache.model.AttachmentCached
import com.ruigoncalo.data.cache.model.MessageCached

@Dao
abstract class MessagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMessages(messages: List<MessageCached>)

    @Query("SELECT * FROM messages")
    abstract fun getMessages(): List<MessageCached>

    @Query("DELETE FROM messages WHERE id = :messageId")
    abstract fun deleteMessage(messageId: Long)

    @Query("UPDATE messages SET attachments = :attachments WHERE id = :messageId")
    abstract fun updateAttachment(messageId: Long, attachments: List<AttachmentCached>)
}