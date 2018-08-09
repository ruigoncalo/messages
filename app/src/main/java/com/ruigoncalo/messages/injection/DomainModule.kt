package com.ruigoncalo.messages.injection

import com.ruigoncalo.domain.DeleteAttachmentInteractor
import com.ruigoncalo.domain.DeleteAttachmentUseCase
import com.ruigoncalo.domain.DeleteMessageInteractor
import com.ruigoncalo.domain.DeleteMessageUseCase
import com.ruigoncalo.domain.GetMessagesUseCase
import com.ruigoncalo.domain.RetrieveMessagesInteractor
import com.ruigoncalo.domain.model.Messages
import dagger.Binds
import dagger.Module

@Module
abstract class DomainModule {

    @Binds
    abstract fun bindsRetrieveMessagesInteractor(getMessagesUseCase: GetMessagesUseCase): RetrieveMessagesInteractor<String, Messages>

    @Binds
    abstract fun bindsDeleteMessageInteractor(deleteMessageUseCase: DeleteMessageUseCase): DeleteMessageInteractor<String>

    @Binds
    abstract fun bindsDeleteAttachmentInteractor(deleteAttachmentUseCase: DeleteAttachmentUseCase): DeleteAttachmentInteractor<String>

   /* @Provides
    fun provideRetrieveMessagesInteractor(repository: Repository<String, Messages>): RetrieveMessagesInteractor<String, Messages> {
        return GetMessagesUseCase(repository)
    }

    @Provides
    fun provideDeleteMessageInteractor(repository: Repository<String, Messages>): DeleteMessageInteractor<String> {
        return DeleteMessageUseCase(repository)
    }

    @Provides
    fun provideDeleteAttachmentInteractor(repository: Repository<String, Messages>): DeleteAttachmentInteractor<String> {
        return DeleteAttachmentUseCase(repository)
    }*/

}