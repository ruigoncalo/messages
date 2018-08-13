package com.ruigoncalo.domain

import io.reactivex.Completable
import javax.inject.Inject

class DeleteMessageUseCase @Inject constructor(private val repository: Repository) : DeleteMessageInteractor {

    override fun deleteMessage(messageId: Long): Completable {
        return repository.deleteMessage(messageId)
    }
}