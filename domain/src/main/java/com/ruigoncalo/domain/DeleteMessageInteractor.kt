package com.ruigoncalo.domain

import io.reactivex.Completable

interface DeleteMessageInteractor<Params> {

    fun deleteMessage(params: Params): Completable
}