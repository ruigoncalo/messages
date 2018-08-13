package com.ruigoncalo.domain

import com.ruigoncalo.domain.model.Attachment
import com.ruigoncalo.domain.model.Messages
import io.reactivex.Completable
import io.reactivex.Observable
import polanski.option.Option

interface Repository {

    fun getMessages(): Observable<Option<Messages>>

    fun fetchMessages(): Completable

    fun deleteMessage(messageId: Long): Completable

    fun updateAttachment(messageId: Long, attachments: List<Attachment>): Completable
}