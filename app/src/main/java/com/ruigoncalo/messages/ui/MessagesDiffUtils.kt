package com.ruigoncalo.messages.ui

import android.support.v7.util.DiffUtil
import com.ruigoncalo.messages.presentation.model.ItemEntity

class MessagesDiffUtils(private val oldMessages: List<ItemEntity>,
                        private val newMessages: List<ItemEntity>) : DiffUtil.Callback() {

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMessages[oldItemPosition].getItemId() == newMessages[newItemPosition].getItemId()
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMessages[oldItemPosition].getItemId() == newMessages[newItemPosition].getItemId()
    }

    override fun getNewListSize(): Int {
        return newMessages.size
    }

    override fun getOldListSize(): Int {
        return oldMessages.size
    }
}