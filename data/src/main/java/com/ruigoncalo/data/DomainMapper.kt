package com.ruigoncalo.data

import com.ruigoncalo.data.cache.model.AttachmentCached
import com.ruigoncalo.data.cache.model.MessageCached
import com.ruigoncalo.data.cache.model.MessagesCached
import com.ruigoncalo.data.cache.model.UserCached
import com.ruigoncalo.domain.Mapper
import com.ruigoncalo.domain.model.Attachment
import com.ruigoncalo.domain.model.Message
import com.ruigoncalo.domain.model.Messages
import com.ruigoncalo.domain.model.User
import javax.inject.Inject

class DomainMapper @Inject constructor() : Mapper<MessagesCached, Messages> {

    override fun map(r: MessagesCached): Messages {
        return Messages(messages = r.messages.mapNotNull { it.toDomain(r.users) })
    }
}

fun MessageCached.toDomain(users: List<UserCached>): Message? {
    return users.firstOrNull { it.id == this.userId }?.let {
        Message(this.id, it.toDomain(), this.content,
                this.attachments.let { it.map { it.toDomain() } })
    }
}

fun AttachmentCached.toDomain(): Attachment {
    return Attachment(this.id, this.title, this.url, this.thumbnailUrl)
}

fun UserCached.toDomain(): User {
    return User(this.id, this.name, this.avatarId)
}

fun Attachment.toCacheModel(): AttachmentCached {
    return AttachmentCached(this.id, this.title, this.url, this.thumbnailUrl)
}