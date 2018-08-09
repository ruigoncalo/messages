package com.ruigoncalo.data

import com.ruigoncalo.data.model.AttachmentRaw
import com.ruigoncalo.data.model.MessageRaw
import com.ruigoncalo.data.model.MessagesRaw
import com.ruigoncalo.data.model.UserRaw
import com.ruigoncalo.domain.Mapper
import com.ruigoncalo.domain.model.Attachment
import com.ruigoncalo.domain.model.Message
import com.ruigoncalo.domain.model.Messages
import com.ruigoncalo.domain.model.User
import javax.inject.Inject

class MessagesMapper @Inject constructor() : Mapper<MessagesRaw, Messages> {

    override fun map(r: MessagesRaw): Messages {
        return Messages(messages = r.messages.mapNotNull { raw -> raw.toDomainModel(r.users) })
    }
}

fun MessageRaw.toDomainModel(users: List<UserRaw>): Message? {
    return users.firstOrNull { it.id == this.userId }?.let {
        Message(this.id, it.toDomainModel(), this.content,
                this.attachments?.let { it.map { it.toDomainModel() } } ?: listOf())
    }
}

fun AttachmentRaw.toDomainModel(): Attachment {
    return Attachment(this.id, this.title, this.url, this.thumbnailUrl)
}

fun UserRaw.toDomainModel(): User {
    return User(this.id, this.name, this.avatarId)
}