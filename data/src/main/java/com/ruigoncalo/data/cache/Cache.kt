package com.ruigoncalo.data.cache

interface Cache<Key, Value> {

    fun get(key: Key): Value

    fun put(key: Key, value: Value)
}