package com.ruigoncalo.data.cache

import com.ruigoncalo.data.cache.model.AttachmentCached
import com.ruigoncalo.data.cache.model.MessageCached
import com.ruigoncalo.data.cache.model.UserCached
import javax.inject.Inject

class MessagesCache @Inject constructor(private val database: MessagesDatabase) : Cache {

    override fun saveMessages(messages: List<MessageCached>) {
        return database.cachedMessagesDao().insertMessages(messages)
    }

    override fun saveUsers(users: List<UserCached>) {
        return database.cachedUsersDao().insertUsers(users)
    }

    override fun getUsers(): List<UserCached> {
        return database.cachedUsersDao().getUsers()
    }

    override fun getUser(userId: Long): UserCached {
        return database.cachedUsersDao().getUser(userId)
    }

    override fun getMessages(): List<MessageCached> {
        return database.cachedMessagesDao().getMessages()
    }

    override fun deleteMessage(messageId: Long) {
        return database.cachedMessagesDao().deleteMessage(messageId)
    }

    override fun updateAttachments(messageId: Long, attachments: List<AttachmentCached>) {
        return database.cachedMessagesDao().updateAttachment(messageId, attachments)
    }
}