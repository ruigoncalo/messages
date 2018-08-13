package com.ruigoncalo.messages.ui

import com.ruigoncalo.messages.presentation.model.AttachmentViewEntity
import com.ruigoncalo.messages.presentation.model.MessageViewEntity

interface ItemLongClickListener {

    fun onMessageLongPress(position: Int, message: MessageViewEntity)

    fun onAttachmentLongPress(position: Int, attachment: AttachmentViewEntity)
}