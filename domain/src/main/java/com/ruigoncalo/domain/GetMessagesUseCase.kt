package com.ruigoncalo.domain

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import polanski.option.Option
import polanski.option.OptionUnsafe
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(
        private val repository: Repository<Int, Messages>): RetrieveMessagesInteractor<Int, Messages> {

    override fun retrieve(params: Int): Observable<Messages> {
        return repository.getMessages(params)
                .flatMapSingle { fetchWhenNoneAndThenRetrieve(it) }
                .filter(Option<Messages>::isSome)
                .map { OptionUnsafe.getUnsafe(it) }
    }

    private fun fetchWhenNoneAndThenRetrieve(messages: Option<Messages>): Single<Option<Messages>> {
        return fetchWhenNone(messages).andThen(Single.just(messages))
    }

    private fun fetchWhenNone(messages: Option<Messages>): Completable {
        return if (messages.isNone) repository.fetchMessages() else Completable.complete()
    }
}