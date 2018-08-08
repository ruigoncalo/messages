package com.ruigoncalo.domain.model

data class Message(val id: Long,
                   val userId: Long,
                   val content: String,
                   val attachments: List<Attachment>)