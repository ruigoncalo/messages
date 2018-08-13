package com.ruigoncalo.data.store

import com.ruigoncalo.data.cache.Cache
import com.ruigoncalo.data.cache.model.AttachmentCached
import com.ruigoncalo.data.cache.model.MessageCached
import com.ruigoncalo.data.cache.model.MessagesCached
import com.ruigoncalo.data.cache.model.UserCached
import com.ruigoncalo.domain.Mapper
import com.ruigoncalo.domain.model.Messages
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import polanski.option.Option
import javax.inject.Inject

class ReactiveStore @Inject constructor(private val cache: Cache,
                                        private val domainMapper: Mapper<MessagesCached, Messages>) : Store {

    private val subject: PublishSubject<Option<Messages>> = PublishSubject.create()

    override fun getMessages(): Observable<Option<Messages>> {
        return Observable.defer {
            Observable.just(cache.getMessages())
                    .zipWith(Observable.just(cache.getUsers()),
                            BiFunction<List<MessageCached>, List<UserCached>, MessagesCached> { messages, users ->
                                MessagesCached(messages, users)
                            })
                    .map(domainMapper::map)
                    .map { Option.ofObj(it) }
        }
                .onErrorResumeNext { _: Throwable -> Observable.just<Option<Messages>>(Option.none()) }
                .concatWith(subject)
    }

    override fun putMessages(messages: MessagesCached): Completable {
        return Completable.fromCallable {
            cache.saveMessages(messages.messages)
            cache.saveUsers(messages.users)
            subject.onNext(Option.ofObj(domainMapper.map(messages)))
        }
    }

    override fun deleteMessage(messageId: Long): Completable {
        return Completable.fromCallable {
            cache.deleteMessage(messageId)
        }
    }

    override fun updateAttachment(messageId: Long, attachments: List<AttachmentCached>): Completable {
        return Completable.fromCallable {
            cache.updateAttachments(messageId, attachments)
        }
    }

}