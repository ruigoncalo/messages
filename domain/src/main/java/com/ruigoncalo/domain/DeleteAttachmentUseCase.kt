package com.ruigoncalo.domain

import com.ruigoncalo.domain.model.Attachment
import io.reactivex.Completable
import javax.inject.Inject

class DeleteAttachmentUseCase @Inject constructor(private val repository: Repository) : DeleteAttachmentInteractor {

    override fun deleteAttachment(messageId: Long, attachments: List<Attachment>): Completable {
        return repository.updateAttachment(messageId, attachments)
    }
}