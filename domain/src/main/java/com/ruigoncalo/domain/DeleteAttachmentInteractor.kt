package com.ruigoncalo.domain

import io.reactivex.Completable

interface DeleteAttachmentInteractor<Params> {

    fun deleteAttachment(params: Params): Completable
}