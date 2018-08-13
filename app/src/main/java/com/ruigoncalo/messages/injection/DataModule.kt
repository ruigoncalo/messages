package com.ruigoncalo.messages.injection

import android.app.Application
import android.content.Context
import com.ruigoncalo.data.DomainMapper
import com.ruigoncalo.data.MessagesRepository
import com.ruigoncalo.data.StoreMapper
import com.ruigoncalo.data.cache.Cache
import com.ruigoncalo.data.cache.MessagesCache
import com.ruigoncalo.data.cache.MessagesDatabase
import com.ruigoncalo.data.cache.model.MessagesCached
import com.ruigoncalo.data.external.LocalJsonParser
import com.ruigoncalo.data.external.Parser
import com.ruigoncalo.data.external.model.MessagesRaw
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
    fun providesDatabase(application: Application): MessagesDatabase {
        return MessagesDatabase.getInstance(application)
    }

    @Provides
    @Singleton
    fun providesCache(database: MessagesDatabase): Cache {
        return MessagesCache(database)
    }

    @Provides
    fun providesStore(cache: Cache, mapper: Mapper<MessagesCached, Messages>): Store {
        return ReactiveStore(cache, mapper)
    }

    @Provides
    fun providesParser(context: Context): Parser {
        return LocalJsonParser(context)
    }

    @Provides
    fun providesStoreMapper(): Mapper<MessagesRaw, MessagesCached> {
        return StoreMapper()
    }

    @Provides
    fun providesDomainMapper(): Mapper<MessagesCached, Messages> {
        return DomainMapper()
    }

    @Provides
    @Singleton
    fun providesRepository(parser: Parser,
                           store: Store,
                           mapper: Mapper<MessagesRaw, MessagesCached>): Repository {
        return MessagesRepository(parser, store, mapper)
    }
}