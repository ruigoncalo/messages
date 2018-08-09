package com.ruigoncalo.data

import com.ruigoncalo.data.external.Parser
import com.ruigoncalo.data.model.MessagesRaw
import com.ruigoncalo.data.store.Store
import com.ruigoncalo.domain.Mapper
import com.ruigoncalo.domain.Repository
import com.ruigoncalo.domain.model.Messages
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import polanski.option.Option
import javax.inject.Inject

class MessagesRepository @Inject constructor(
        private val parser: Parser,
        private val store: Store<String, Messages>,
        private val mapper: Mapper<MessagesRaw, Messages>) : Repository<String, Messages> {

    override fun getMessages(params: String): Observable<Option<Messages>> {
        return store.get(params)
    }

    override fun fetchMessages(): Completable {
        return parser.parseFile()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(mapper::map)
                .flatMapCompletable { messages -> store.put("key", messages) }
    }

    override fun deleteMessage(params: String): Completable {
        return Completable.complete()
    }

    override fun deleteAttachment(params: String): Completable {
        return Completable.complete()
    }
}