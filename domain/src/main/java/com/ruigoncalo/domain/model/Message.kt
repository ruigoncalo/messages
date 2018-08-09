package com.ruigoncalo.domain.model

data class Message(val id: Long,
                   val user: User,
                   val content: String,
                   val attachments: List<Attachment>)