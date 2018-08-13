package com.ruigoncalo.data.store

import com.ruigoncalo.data.cache.model.AttachmentCached
import com.ruigoncalo.data.cache.model.MessagesCached
import com.ruigoncalo.domain.model.Messages
import io.reactivex.Completable
import io.reactivex.Observable
import polanski.option.Option

interface Store {

    fun getMessages(): Observable<Option<Messages>>

    fun putMessages(messages: MessagesCached): Completable

    fun deleteMessage(messageId: Long): Completable

    fun updateAttachment(messageId: Long, attachments: List<AttachmentCached>): Completable
}