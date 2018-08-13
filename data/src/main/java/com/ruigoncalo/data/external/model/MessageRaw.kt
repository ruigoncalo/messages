package com.ruigoncalo.data.external.model

data class MessageRaw(val id: Long,
                      val userId: Long,
                      val content: String,
                      val attachments: List<AttachmentRaw>?)