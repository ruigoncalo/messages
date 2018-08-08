package com.ruigoncalo.domain

import io.reactivex.Completable

interface DeleteAttachmentInteractor<in Params> {

    fun deleteAttachment(params: Params): Completable
}