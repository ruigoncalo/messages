package com.ruigoncalo.data.cache.model

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListTypeConverter {

    companion object {
        @TypeConverter
        @JvmStatic
        fun stringToAttachments(data: String?): List<AttachmentCached> {
            return data?.let {
                Gson().fromJson<List<AttachmentCached>>(data, object : TypeToken<List<AttachmentCached>>() {}.type)
            } ?: listOf()
        }

        @TypeConverter
        @JvmStatic
        fun attachmentsToString(list: List<AttachmentCached>): String {
            return Gson().toJson(list)
        }
    }
}