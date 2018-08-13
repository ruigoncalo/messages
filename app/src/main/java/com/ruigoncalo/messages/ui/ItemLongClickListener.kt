package com.ruigoncalo.messages.ui

import com.ruigoncalo.messages.presentation.model.AttachmentViewEntity
import com.ruigoncalo.messages.presentation.model.MessageViewEntity

interface ItemLongClickListener {

    fun onMessageLongPress(message: MessageViewEntity)

    fun onAttachmentLongPress(attachment: AttachmentViewEntity)
}