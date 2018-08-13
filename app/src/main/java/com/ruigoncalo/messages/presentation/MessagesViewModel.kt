package com.ruigoncalo.messages.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ruigoncalo.domain.DeleteAttachmentInteractor
import com.ruigoncalo.domain.DeleteMessageInteractor
import com.ruigoncalo.domain.GetMessagesInteractor
import com.ruigoncalo.domain.Mapper
import com.ruigoncalo.domain.model.Messages
import com.ruigoncalo.messages.presentation.model.AttachmentViewEntity
import com.ruigoncalo.messages.presentation.model.MessageViewEntity
import com.ruigoncalo.messages.presentation.model.MessagesViewEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import polanski.option.OptionUnsafe
import javax.inject.Inject

class MessagesViewModel @Inject constructor(
        private val getMessagesInteractor: GetMessagesInteractor,
        private val deleteMessageInteractor: DeleteMessageInteractor,
        private val deleteAttachmentInteractor: DeleteAttachmentInteractor,
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
                getMessagesInteractor.getMessages("")
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext { messagesLiveData.value = ViewResource.loading() }
                        .observeOn(Schedulers.computation())
                        .map { mapper.map(it) }
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            messagesLiveData.postValue(ViewResource.success(it))
                        }, { e ->
                            messagesLiveData.postValue(ViewResource.error(e.message))
                        })
        )
    }

    fun deleteMessage(message: MessageViewEntity) {
        disposables.add(
                deleteMessageInteractor.deleteMessage(message.id)
                        .subscribeOn(Schedulers.io())
                        .subscribe()
        )
    }

    fun deleteAttachment(attachment: AttachmentViewEntity) {
        messagesLiveData.value?.let {
            val message = OptionUnsafe.getUnsafe(it.data).messages.first { it.getItemId() == attachment.messageId } as MessageViewEntity
            val filteredAttachments = message.attachments.filter { it.id != attachment.id }.map { it.toDomainModel() }

            disposables.add(
                    deleteAttachmentInteractor.deleteAttachment(message.id, filteredAttachments)
                            .subscribeOn(Schedulers.io())
                            .subscribe()
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}