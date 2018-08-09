package com.ruigoncalo.messages.injection

import android.content.Context
import com.ruigoncalo.data.MessagesMapper
import com.ruigoncalo.data.MessagesRepository
import com.ruigoncalo.data.cache.Cache
import com.ruigoncalo.data.cache.MemoryCache
import com.ruigoncalo.data.external.LocalJsonParser
import com.ruigoncalo.data.external.Parser
import com.ruigoncalo.data.model.MessagesRaw
import com.ruigoncalo.data.store.ReactiveStore
import com.ruigoncalo.data.store.Store
import com.ruigoncalo.domain.Mapper
import com.ruigoncalo.domain.Repository
import com.ruigoncalo.domain.model.Messages
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun providesCache(): Cache<String, Messages> {
        return MemoryCache()
    }

    @Provides
    fun providesStore(cache: Cache<String, Messages>): Store<String, Messages> {
        return ReactiveStore(cache)
    }

    @Provides
    fun providesParser(context: Context): Parser {
        return LocalJsonParser(context)
    }

    @Provides
    fun providesMapper(): Mapper<MessagesRaw, Messages> {
        return MessagesMapper()
    }

    @Provides
    @Singleton
    fun providesRepository(parser: Parser,
                           store: Store<String, Messages>,
                           mapper: Mapper<MessagesRaw, Messages>): Repository<String, Messages> {
        return MessagesRepository(parser, store, mapper)
    }
}