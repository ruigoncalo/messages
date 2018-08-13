package com.ruigoncalo.data

import com.ruigoncalo.data.cache.model.AttachmentCached
import com.ruigoncalo.data.cache.model.MessageCached
import com.ruigoncalo.data.cache.model.MessagesCached
import com.ruigoncalo.data.cache.model.UserCached
import com.ruigoncalo.data.external.model.AttachmentRaw
import com.ruigoncalo.data.external.model.MessageRaw
import com.ruigoncalo.data.external.model.MessagesRaw
import com.ruigoncalo.data.external.model.UserRaw
import com.ruigoncalo.domain.Mapper
import javax.inject.Inject

class StoreMapper @Inject constructor() : Mapper<MessagesRaw, MessagesCached> {

    override fun map(r: MessagesRaw): MessagesCached {
        return MessagesCached(messages = r.messages.map { raw -> raw.toCached() },
                users = r.users.map { it.toCached() })
    }
}

fun MessageRaw.toCached(): MessageCached {
    return MessageCached(this.id, this.userId, this.content,
            this.attachments?.let { it.map { it.toCached() } } ?: listOf())
}

fun AttachmentRaw.toCached(): AttachmentCached {
    return AttachmentCached(this.id, this.title, this.url, this.thumbnailUrl)
}

fun UserRaw.toCached(): UserCached {
    return UserCached(this.id, this.name, this.avatarId)
}