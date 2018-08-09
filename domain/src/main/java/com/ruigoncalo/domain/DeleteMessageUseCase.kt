package com.ruigoncalo.domain

import com.ruigoncalo.domain.model.Messages
import io.reactivex.Completable
import javax.inject.Inject

class DeleteMessageUseCase @Inject constructor(
        private val repository: Repository<String, Messages>) : DeleteMessageInteractor<String> {

    override fun deleteMessage(params: String): Completable {
        return repository.deleteMessage(params)
    }
}