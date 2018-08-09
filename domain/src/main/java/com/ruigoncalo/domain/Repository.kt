package com.ruigoncalo.domain

import io.reactivex.Completable
import io.reactivex.Observable
import polanski.option.Option

interface Repository<Params, Value> {

    fun getMessages(params: Params): Observable<Option<Value>>

    fun fetchMessages(): Completable

    fun deleteMessage(params: Params): Completable

    fun deleteAttachment(params: Params): Completable
}