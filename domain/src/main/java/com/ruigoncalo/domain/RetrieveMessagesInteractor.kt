package com.ruigoncalo.domain

import io.reactivex.Observable

interface RetrieveMessagesInteractor<Params, Result> {

    fun retrieve(params: Params): Observable<Result>
}