package com.ruigoncalo.messages.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ruigoncalo.domain.DeleteAttachmentInteractor
import com.ruigoncalo.domain.DeleteMessageInteractor
import com.ruigoncalo.domain.Mapper
import com.ruigoncalo.domain.RetrieveMessagesInteractor
import com.ruigoncalo.domain.model.Messages
import com.ruigoncalo.messages.presentation.model.MessagesViewEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MessagesViewModel @Inject constructor(
        private val retrieveMessagesInteractor: RetrieveMessagesInteractor<String, Messages>,
        private val deleteMessageInteractor: DeleteMessageInteractor<String>,
        private val deleteAttachmentInteractor: DeleteAttachmentInteractor<String>,
        private val mapper: Mapper<Messages, MessagesViewEntity>) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val messagesLiveData: MutableLiveData<ViewResource<MessagesViewEntity>> = MutableLiveData()

    init {
        retrieveMessages()
    }

    fun getMessagesLiveData(): MutableLiveData<ViewResource<MessagesViewEntity>> {
        return messagesLiveData
    }

    private fun retrieveMessages() {
        disposables.add(
                retrieveMessagesInteractor.retrieve("")
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext { messagesLiveData.value = ViewResource.loading() }
                        .observeOn(Schedulers.computation())
                        .map { mapper.map(it) }
                        .subscribe({
                            messagesLiveData.postValue(ViewResource.success(it))
                        }, { e ->
                            messagesLiveData.postValue(ViewResource.error(e.message))
                        })
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}