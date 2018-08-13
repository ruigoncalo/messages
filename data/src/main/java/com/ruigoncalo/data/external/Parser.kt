package com.ruigoncalo.data.external

import com.ruigoncalo.data.external.model.MessagesRaw
import io.reactivex.Single

interface Parser {

    fun parseFile(): Single<MessagesRaw>
}