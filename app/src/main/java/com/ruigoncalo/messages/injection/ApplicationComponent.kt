package com.ruigoncalo.messages.injection

import android.app.Application
import com.ruigoncalo.messages.MessagesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
    ApplicationModule::class,
    UiModule::class,
    PresentationModule::class,
    DataModule::class,
    DomainModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MessagesApplication)
}