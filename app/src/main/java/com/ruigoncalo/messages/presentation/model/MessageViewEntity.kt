package com.ruigoncalo.messages.presentation.model

data class MessageViewEntity(val id: Long,
                             val user: UserViewEntity,
                             val content: String,
                             val attachments: List<AttachmentViewEntity>) : ItemEntity {

    override fun getItemType(): ItemType {
        return if (user.id == 1L) ItemType.MESSAGE_ME else ItemType.MESSAGE
    }

    override fun getItemId(): Long {
        return id
    }
}