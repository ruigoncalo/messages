package com.ruigoncalo.messages.presentation.model

interface ItemEntity {
    fun getItemType(): ItemType

    fun getItemId(): Long
}