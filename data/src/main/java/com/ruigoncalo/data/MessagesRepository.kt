package com.ruigoncalo.data

import com.ruigoncalo.data.cache.model.MessagesCached
import com.ruigoncalo.data.external.Parser
import com.ruigoncalo.data.external.model.MessagesRaw
import com.ruigoncalo.data.store.Store
import com.ruigoncalo.domain.Mapper
import com.ruigoncalo.domain.Repository
import com.ruigoncalo.domain.model.Attachment
import com.ruigoncalo.domain.model.Messages
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import polanski.option.Option
import javax.inject.Inject

class MessagesRepository @Inject constructor(
        private val parser: Parser,
        private val store: Store,
        private val storeMapper: Mapper<MessagesRaw, MessagesCached>) : Repository {

    override fun getMessages(): Observable<Option<Messages>> {
        return store.getMessages()
    }

    override fun fetchMessages(): Completable {
        return parser.parseFile()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(storeMapper::map)
                .flatMapCompletable { messages -> store.putMessages(messages) }
    }

    override fun deleteMessage(messageId: Long): Completable {
        return store.deleteMessage(messageId)
    }

    override fun updateAttachment(messageId: Long, attachments: List<Attachment>): Completable {
        return store.updateAttachment(messageId, attachments.map { it.toCacheModel() })
    }
}