package com.ruigoncalo.messages.injection

import com.ruigoncalo.domain.DeleteAttachmentInteractor
import com.ruigoncalo.domain.DeleteAttachmentUseCase
import com.ruigoncalo.domain.DeleteMessageInteractor
import com.ruigoncalo.domain.DeleteMessageUseCase
import com.ruigoncalo.domain.GetMessagesInteractor
import com.ruigoncalo.domain.GetMessagesUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class DomainModule {

    @Binds
    abstract fun bindsRetrieveMessagesInteractor(getMessagesUseCase: GetMessagesUseCase): GetMessagesInteractor

    @Binds
    abstract fun bindsDeleteMessageInteractor(deleteMessageUseCase: DeleteMessageUseCase): DeleteMessageInteractor

    @Binds
    abstract fun bindsDeleteAttachmentInteractor(deleteAttachmentUseCase: DeleteAttachmentUseCase): DeleteAttachmentInteractor
}