package com.ruigoncalo.messages.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ruigoncalo.messages.R
import com.ruigoncalo.messages.presentation.model.MessageViewEntity
import com.ruigoncalo.messages.presentation.model.MessagesViewEntity

class MessagesAdapter(private val context: Context) : RecyclerView.Adapter<MessageItemView>() {

    private val inflater by lazy { LayoutInflater.from(context) }

    private val messages = mutableListOf<MessageViewEntity>()

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageItemView {
        val view = inflater.inflate(R.layout.layout_message, parent, false)
        return MessageItemView(view)
    }

    override fun onBindViewHolder(holder: MessageItemView, position: Int) {
        holder.bind(messages[position])
    }

    fun addMessages(messagesViewEntity: MessagesViewEntity) {
        this.messages.addAll(messagesViewEntity.messages)
        notifyDataSetChanged()
    }
}