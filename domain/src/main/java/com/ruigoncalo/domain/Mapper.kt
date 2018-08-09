package com.ruigoncalo.domain

interface Mapper<R, V> {
    fun map(r: R): V
}