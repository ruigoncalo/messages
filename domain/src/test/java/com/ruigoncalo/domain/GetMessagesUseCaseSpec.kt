package com.ruigoncalo.domain

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.ruigoncalo.domain.model.Messages
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import polanski.option.Option

class GetMessagesUseCaseSpec : Spek({

    val repository: Repository = mock()
    val tested = GetMessagesUseCase(repository)
    var testObserver = TestObserver<Messages>()

    val mockMessages = Messages(messages = listOf())

    beforeEachTest {
        reset(repository)
        whenever(repository.fetchMessages()).thenReturn(Completable.complete())
        testObserver = TestObserver()
    }

    describe("get messages") {

        context("there are no messages to retrieve") {
            beforeEachTest {
                whenever(repository.getMessages()).thenReturn(Observable.just(Option.none()))
                tested.getMessages().subscribe(testObserver)
            }

            it("should fetch messages from repository") {
                verify(repository).fetchMessages()
            }

            it("should return nothing and complete") {
                with(testObserver) {
                    assertNoValues()
                    assertNoErrors()
                    assertComplete()
                }
            }
        }

        context("there are messages to retrieve") {
            beforeEachTest {
                whenever(repository.getMessages()).thenReturn(Observable.just(Option.ofObj(mockMessages)))
                tested.getMessages().subscribe(testObserver)
            }


            it("should not fetch messages from repository") {
                verify(repository, never()).fetchMessages()
            }

            it("should return messages and complete") {
                with(testObserver) {
                    assertValue(mockMessages)
                    assertNoErrors()
                    assertComplete()
                }
            }
        }
    }
})