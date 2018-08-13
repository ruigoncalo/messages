package com.ruigoncalo.messages.ui

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ruigoncalo.messages.R
import com.ruigoncalo.messages.presentation.model.AttachmentViewEntity
import com.ruigoncalo.messages.presentation.model.ItemEntity
import com.ruigoncalo.messages.presentation.model.ItemType
import com.ruigoncalo.messages.presentation.model.MessageViewEntity

class GenericItemView(private val view: View,
                      private val listener: ItemLongClickListener) : RecyclerView.ViewHolder(view) {

    fun bind(entity: ItemEntity) {
        when (entity.getItemType()) {
            ItemType.MESSAGE -> bindMessage(entity as MessageViewEntity)
            ItemType.MESSAGE_ME -> bindMessageMe(entity as MessageViewEntity)
            ItemType.ATTACHMENT, ItemType.ATTACHMENT_ME -> bindAttachment(entity as AttachmentViewEntity)
        }
    }

    private fun bindMessage(message: MessageViewEntity) {
        val imageView = view.findViewById<ImageView>(R.id.userImageView)
        val nameView = view.findViewById<TextView>(R.id.nameView)
        val contentView = view.findViewById<TextView>(R.id.contentView)

        imageView.loadImage(message.user.avatarId,
                ContextCompat.getDrawable(view.context, R.drawable.ic_launcher_background),
                ContextCompat.getDrawable(view.context, R.drawable.ic_face))
        nameView.text = message.user.name
        contentView.text = message.content

        view.setOnLongClickListener {
            listener.onMessageLongPress(adapterPosition, message)
            true
        }
    }

    private fun bindMessageMe(message: MessageViewEntity) {
        val nameView = view.findViewById<TextView>(R.id.nameView)
        val contentView = view.findViewById<TextView>(R.id.contentView)

        nameView.text = "Me"
        contentView.text = message.content

        view.setOnLongClickListener {
            listener.onMessageLongPress(adapterPosition, message)
            true
        }
    }

    private fun bindAttachment(attachment: AttachmentViewEntity) {
        val imageView = view.findViewById<ImageView>(R.id.attachmentImageView)
        val textView = view.findViewById<TextView>(R.id.attachmentTextView)

        imageView.loadImage(attachment.url, null, null)
        textView.text = attachment.title

        view.setOnLongClickListener {
            listener.onAttachmentLongPress(adapterPosition, attachment)
            true
        }
    }
}