package com.ruigoncalo.domain

import io.reactivex.Observable

interface RetrieveMessagesInteractor<in Params, Result> {

    fun retrieve(params: Params): Observable<Result>
}