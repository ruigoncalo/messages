package com.ruigoncalo.data.external

import android.content.Context
import com.google.gson.Gson
import com.ruigoncalo.data.model.MessagesRaw
import io.reactivex.Single
import javax.inject.Inject

class LocalJsonParser @Inject constructor(private val context: Context) : Parser {

    private val gson by lazy { Gson() }

    override fun parseFile(): Single<MessagesRaw> {
        return Single.defer {
            Single.create<MessagesRaw> {
                val json: String
                try {
                    val inputStream = context.assets.open("data.json")
                    val size = inputStream.available()
                    val buffer = ByteArray(size)
                    inputStream.read(buffer)
                    inputStream.close()
                    json = String(buffer, Charsets.UTF_8)

                    val value = gson.fromJson<MessagesRaw>(json, MessagesRaw::class.java)
                    it.onSuccess(value)
                } catch (e: Exception) {
                    it.onError(e)
                }
            }
        }
    }
}