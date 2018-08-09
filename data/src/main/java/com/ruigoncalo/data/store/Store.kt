package com.ruigoncalo.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import polanski.option.Option

interface Store<Key, Value> {

    fun get(key: Key): Observable<Option<Value>>

    fun put(key: String, value: Value): Completable
}