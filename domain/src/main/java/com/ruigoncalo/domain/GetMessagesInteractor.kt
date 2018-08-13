package com.ruigoncalo.domain

import com.ruigoncalo.domain.model.Messages
import io.reactivex.Observable

interface GetMessagesInteractor {

    fun getMessages(): Observable<Messages>
}