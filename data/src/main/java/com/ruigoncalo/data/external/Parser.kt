package com.ruigoncalo.data.external

import com.ruigoncalo.data.model.DataRaw
import io.reactivex.Single

interface Parser {

    fun parseFile(filePath: String): Single<DataRaw>
}