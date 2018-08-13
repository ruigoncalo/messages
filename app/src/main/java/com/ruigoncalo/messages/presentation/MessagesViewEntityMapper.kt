package com.ruigoncalo.messages.presentation

import com.ruigoncalo.domain.Mapper
import com.ruigoncalo.domain.model.Attachment
import com.ruigoncalo.domain.model.Message
import com.ruigoncalo.domain.model.Messages
import com.ruigoncalo.domain.model.User
import com.ruigoncalo.messages.presentation.model.AttachmentViewEntity
import com.ruigoncalo.messages.presentation.model.ItemEntity
import com.ruigoncalo.messages.presentation.model.MessageViewEntity
import com.ruigoncalo.messages.presentation.model.MessagesViewEntity
import com.ruigoncalo.messages.presentation.model.UserViewEntity
import javax.inject.Inject

class MessagesViewEntityMapper @Inject constructor() : Mapper<Messages, MessagesViewEntity> {

    override fun map(r: Messages): MessagesViewEntity {
        val items = mutableListOf<ItemEntity>()
        r.messages.forEach { message ->
            items.add(message.toViewEntity())

            message.attachments.forEach { attachment ->
                items.add(attachment.toViewEntity(message))
            }
        }

        return MessagesViewEntity(items)
    }
}

fun Message.toViewEntity(): MessageViewEntity {
    return MessageViewEntity(this.id, this.user.toViewEntity(), this.content)
}

fun Attachment.toViewEntity(message: Message): AttachmentViewEntity {
    return AttachmentViewEntity(this.id, message.toViewEntity(), this.title, this.url, this.thumbnailUrl)
}

fun User.toViewEntity(): UserViewEntity {
    return UserViewEntity(this.id, this.name, this.avatarId)
}