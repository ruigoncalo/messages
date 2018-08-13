package com.ruigoncalo.domain

import io.reactivex.Completable

interface DeleteMessageInteractor {

    fun deleteMessage(messageId: Long): Completable
}