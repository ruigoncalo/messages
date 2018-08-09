package com.ruigoncalo.messages.presentation.model

data class MessageViewEntity(val id: Long,
                             val user: UserViewEntity,
                             val content: String,
                             val attachments: List<AttachmentViewEntity>)