package com.ruigoncalo.messages.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ruigoncalo.domain.Mapper
import com.ruigoncalo.domain.model.Messages
import com.ruigoncalo.messages.presentation.MessagesViewEntityMapper
import com.ruigoncalo.messages.presentation.MessagesViewModel
import com.ruigoncalo.messages.presentation.model.MessagesViewEntity
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(MessagesViewModel::class)
    abstract fun bindMessagesViewModel(viewModel: MessagesViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindMapper(mapper: MessagesViewEntityMapper) : Mapper<Messages, MessagesViewEntity>
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)