package com.ruigoncalo.domain

import com.ruigoncalo.domain.model.Attachment
import io.reactivex.Completable

interface DeleteAttachmentInteractor {

    fun deleteAttachment(messageId: Long, attachments: List<Attachment>): Completable
}