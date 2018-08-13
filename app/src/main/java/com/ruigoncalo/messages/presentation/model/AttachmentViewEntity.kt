package com.ruigoncalo.messages.presentation.model

data class AttachmentViewEntity(val id: String,
                                val userId: Long,
                                val messageId: Long,
                                val title: String,
                                val url: String,
                                val thumbnailUrl: String) : ItemEntity {

    override fun getItemType(): ItemType {
        return if (userId == 1L) ItemType.ATTACHMENT_ME else ItemType.ATTACHMENT
    }

    override fun getItemId(): Long {
        return id.hashCode().toLong()
    }
}