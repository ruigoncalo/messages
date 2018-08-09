package com.ruigoncalo.data.store

import com.ruigoncalo.data.cache.Cache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import polanski.option.Option

class ReactiveStore<Value>(private val cache: Cache<String, Value>) : Store<String, Value> {

    private val subject: PublishSubject<Option<Value>> = PublishSubject.create()

    override fun get(key: String): Observable<Option<Value>> {
        return Observable.defer { Observable.just(Option.ofObj(cache.get(key))) }
                .onErrorResumeNext { _: Throwable -> Observable.just<Option<Value>>(Option.none()) }
                .concatWith(subject)
    }

    override fun put(key: String, value: Value): Completable {
        return Completable.fromCallable {
            cache.put(key, value)
            subject.onNext(Option.ofObj(value))
        }
    }
}