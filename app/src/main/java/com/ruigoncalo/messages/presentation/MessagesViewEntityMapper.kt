package com.ruigoncalo.messages.presentation

import com.ruigoncalo.domain.Mapper
import com.ruigoncalo.domain.model.Attachment
import com.ruigoncalo.domain.model.Message
import com.ruigoncalo.domain.model.Messages
import com.ruigoncalo.domain.model.User
import com.ruigoncalo.messages.presentation.model.AttachmentViewEntity
import com.ruigoncalo.messages.presentation.model.MessageViewEntity
import com.ruigoncalo.messages.presentation.model.MessagesViewEntity
import com.ruigoncalo.messages.presentation.model.UserViewEntity
import javax.inject.Inject

class MessagesViewEntityMapper @Inject constructor() : Mapper<Messages, MessagesViewEntity> {

    override fun map(r: Messages): MessagesViewEntity {
        return MessagesViewEntity(r.messages.map { it.toViewEntity() })
    }
}

fun Message.toViewEntity(): MessageViewEntity {
    return MessageViewEntity(this.id, this.user.toViewEntity(), this.content, this.attachments.map { it.toViewEntity() })
}

fun Attachment.toViewEntity(): AttachmentViewEntity {
    return AttachmentViewEntity(this.id, this.title, this.url, this.thumbnailUrl)
}

fun User.toViewEntity(): UserViewEntity {
    return UserViewEntity(this.id, this.name, this.avatarId)
}