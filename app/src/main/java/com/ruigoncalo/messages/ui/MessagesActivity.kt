package com.ruigoncalo.messages.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.ruigoncalo.messages.R
import com.ruigoncalo.messages.injection.ViewModelFactory
import com.ruigoncalo.messages.presentation.MessagesViewModel
import com.ruigoncalo.messages.presentation.ViewResource
import com.ruigoncalo.messages.presentation.ViewResourceState
import com.ruigoncalo.messages.presentation.model.MessagesViewEntity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import polanski.option.OptionUnsafe
import javax.inject.Inject

class MessagesActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MessagesViewModel

    private val messagesAdapter by lazy { MessagesAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MessagesViewModel::class.java)

        setupViews()
        hideLoading()
        hideError()

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getMessagesLiveData().observe(this,
                Observer<ViewResource<MessagesViewEntity>> { resource ->
                    resource?.let {
                        when (it.state) {
                            ViewResourceState.SUCCESS -> {
                                if (it.data.isSome) {
                                    val viewEntity = OptionUnsafe.getUnsafe(it.data)
                                    showMessages(viewEntity)
                                    hideLoading()
                                    hideError()
                                }
                            }

                            ViewResourceState.ERROR -> {
                                if (it.message.isSome) {
                                    showError(OptionUnsafe.getUnsafe(it.message))
                                    hideLoading()
                                }
                            }

                            ViewResourceState.LOADING -> {
                                showLoading()
                                hideError()
                            }
                        }
                    }
                })
    }

    private fun setupViews() {
        with(messagesView) {
            adapter = messagesAdapter.apply {
                setHasStableIds(true)
            }
            layoutManager = LinearLayoutManager(context).apply {
                orientation = RecyclerView.VERTICAL
            }
        }
    }

    private fun showMessages(messages: MessagesViewEntity) {
        messagesAdapter.addMessages(messages)
    }

    private fun hideMessages() {
    }

    private fun showLoading() {
    }

    private fun hideLoading() {
    }

    private fun showError(errorMessage: String) {
    }

    private fun hideError() {
    }
}
