package com.ruigoncalo.domain

import io.reactivex.Completable
import javax.inject.Inject

class DeleteAttachmentUseCase @Inject constructor(
        private val repository: Repository<String, Messages>) : DeleteAttachmentInteractor<String> {

    override fun deleteAttachment(params: String): Completable {
        return repository.deleteAttachment(params)
    }
}