package com.ruigoncalo.messages.presentation.model

data class AttachmentViewEntity(val id: String,
                                val message: MessageViewEntity,
                                val title: String,
                                val url: String,
                                val thumbnailUrl: String) : ItemEntity {

    override fun getItemType(): ItemType {
        return if (message.user.id == 1L) ItemType.ATTACHMENT_ME else ItemType.ATTACHMENT
    }
}