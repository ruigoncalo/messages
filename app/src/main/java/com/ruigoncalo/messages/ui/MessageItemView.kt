package com.ruigoncalo.messages.ui

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ruigoncalo.messages.R
import com.ruigoncalo.messages.presentation.model.MessageViewEntity

class MessageItemView(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(viewEntity: MessageViewEntity) {
        val imageView = view.findViewById<ImageView>(R.id.userImageView)
        val nameView = view.findViewById<TextView>(R.id.nameView)
        val contentView = view.findViewById<TextView>(R.id.contentView)

        imageView.loadImage(viewEntity.user.avatarId,
                ContextCompat.getDrawable(view.context, R.drawable.ic_launcher_background),
                        ContextCompat.getDrawable(view.context, R.drawable.ic_face))
        nameView.text = viewEntity.user.name
        contentView.text = viewEntity.content
    }

}