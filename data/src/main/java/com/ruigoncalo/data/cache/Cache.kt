package com.ruigoncalo.data.cache

import com.ruigoncalo.data.cache.model.AttachmentCached
import com.ruigoncalo.data.cache.model.MessageCached
import com.ruigoncalo.data.cache.model.UserCached

interface Cache {

    fun saveMessages(messages: List<MessageCached>)

    fun saveUsers(users: List<UserCached>)

    fun getUser(userId: Long): UserCached

    fun getUsers(): List<UserCached>

    fun getMessages(): List<MessageCached>

    fun deleteMessage(messageId: Long)

    fun updateAttachments(messageId: Long, attachments: List<AttachmentCached>)
}