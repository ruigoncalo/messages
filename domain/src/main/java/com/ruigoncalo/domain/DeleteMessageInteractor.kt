package com.ruigoncalo.domain

import io.reactivex.Completable

interface DeleteMessageInteractor<in Params> {

    fun deleteMessage(params: Params): Completable
}