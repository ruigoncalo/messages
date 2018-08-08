package com.ruigoncalo.domain

interface Mapper<in R, out V> {
    fun map(r: R): V
}