package com.ruigoncalo.messages.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ruigoncalo.messages.R
import com.ruigoncalo.messages.presentation.model.ItemEntity
import com.ruigoncalo.messages.presentation.model.ItemType
import com.ruigoncalo.messages.presentation.model.MessagesViewEntity

class MessagesAdapter(private val context: Context) : RecyclerView.Adapter<GenericItemView>() {

    companion object {
        const val TYPE_MESSAGE = 0
        const val TYPE_MESSAGE_ME = 1
        const val TYPE_ATTACHMENT = 2
        const val TYPE_ATTACHMENT_ME = 3
    }

    private val inflater by lazy { LayoutInflater.from(context) }

    private val messages = mutableListOf<ItemEntity>()

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (messages[position].getItemType()) {
            ItemType.MESSAGE -> TYPE_MESSAGE
            ItemType.MESSAGE_ME -> TYPE_MESSAGE_ME
            ItemType.ATTACHMENT -> TYPE_ATTACHMENT
            ItemType.ATTACHMENT_ME -> TYPE_ATTACHMENT_ME
        }
    }

    override fun getItemId(position: Int): Long {
        return messages[position].getItemType().id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericItemView {
        val view = when (viewType) {
            TYPE_MESSAGE -> inflater.inflate(R.layout.layout_message, parent, false)
            TYPE_MESSAGE_ME -> inflater.inflate(R.layout.layout_message_me, parent, false)
            TYPE_ATTACHMENT -> inflater.inflate(R.layout.layout_attachment, parent, false)
            else -> inflater.inflate(R.layout.layout_attachment_me, parent, false)
        }

        return GenericItemView(view)
    }

    override fun onBindViewHolder(holder: GenericItemView, position: Int) {
        holder.bind(messages[position])
    }

    fun addMessages(messagesViewEntity: MessagesViewEntity) {
        this.messages.addAll(messagesViewEntity.messages)
        notifyDataSetChanged()
    }
}