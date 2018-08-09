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
        return Messages(
                messages = r.messages.map { raw -> raw.toDomainModel() },
                users = r.users.map { raw -> raw.toDomainModel() }
        )
    }
}

fun MessageRaw.toDomainModel(): Message {
    return Message(this.id, this.userId, this.content, this.attachments.map { it.toDomainModel() })
}

fun AttachmentRaw.toDomainModel(): Attachment {
    return Attachment(this.id, this.title, this.url, this.thumbnailUrl)
}

fun UserRaw.toDomainModel(): User {
    return User(this.id, this.name, this.avatarId)
}