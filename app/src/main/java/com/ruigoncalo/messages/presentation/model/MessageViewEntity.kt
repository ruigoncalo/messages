package com.ruigoncalo.messages.presentation.model

data class MessageViewEntity(val id: Long,
                             val user: UserViewEntity,
                             val content: String) : ItemEntity {

    override fun getItemType(): ItemType {
        return if (user.id == 1L) ItemType.MESSAGE_ME else ItemType.MESSAGE
    }
}