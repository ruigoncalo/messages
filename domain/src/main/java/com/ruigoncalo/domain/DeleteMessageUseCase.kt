package com.ruigoncalo.domain

import io.reactivex.Completable
import sun.misc.resources.Messages
import javax.inject.Inject

class DeleteMessageUseCase @Inject constructor(
        private val repository: Repository<Int, Messages>) : DeleteMessageInteractor<Int> {

    override fun deleteMessage(params: Int): Completable {
        return repository.deleteMessage(params)
    }
}