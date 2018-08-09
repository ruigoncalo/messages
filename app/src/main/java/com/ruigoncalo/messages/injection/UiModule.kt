package com.ruigoncalo.messages.injection

import com.ruigoncalo.messages.ui.MessagesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @ContributesAndroidInjector
    abstract fun contributesMessagesActivity(): MessagesActivity

}