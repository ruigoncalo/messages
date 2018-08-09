package com.ruigoncalo.data.cache

import javax.inject.Inject

class MemoryCache<String, Value> @Inject constructor() : Cache<String, Value> {

    var values: HashMap<String, Value> = hashMapOf()

    override fun get(key: String): Value {
        return values[key] ?: throw IllegalStateException("Key is not stored")
    }

    override fun put(key: String, value: Value) {
        values[key] = value
    }
}