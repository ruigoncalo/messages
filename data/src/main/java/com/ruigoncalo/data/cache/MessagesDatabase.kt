package com.ruigoncalo.data.cache

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.ruigoncalo.data.cache.dao.MessagesDao
import com.ruigoncalo.data.cache.dao.UsersDao
import com.ruigoncalo.data.cache.model.AttachmentCached
import com.ruigoncalo.data.cache.model.ListTypeConverter
import com.ruigoncalo.data.cache.model.MessageCached
import com.ruigoncalo.data.cache.model.UserCached

@Database(entities = [MessageCached::class, AttachmentCached::class, UserCached::class], version = 1)
@TypeConverters(ListTypeConverter::class)
abstract class MessagesDatabase: RoomDatabase() {

    abstract fun cachedMessagesDao(): MessagesDao

    abstract fun cachedUsersDao(): UsersDao

    companion object {

        private var INSTANCE: MessagesDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): MessagesDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                MessagesDatabase::class.java, "messages.db")
                                .build()
                    }
                    return INSTANCE as MessagesDatabase
                }
            }
            return INSTANCE as MessagesDatabase
        }
    }

}