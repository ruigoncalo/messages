package com.ruigoncalo.messages.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import com.ruigoncalo.messages.R
import com.ruigoncalo.messages.injection.ViewModelFactory
import com.ruigoncalo.messages.presentation.MessagesViewModel
import com.ruigoncalo.messages.presentation.ViewResource
import com.ruigoncalo.messages.presentation.ViewResourceState
import com.ruigoncalo.messages.presentation.model.AttachmentViewEntity
import com.ruigoncalo.messages.presentation.model.MessageViewEntity
import com.ruigoncalo.messages.presentation.model.MessagesViewEntity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import polanski.option.OptionUnsafe
import javax.inject.Inject

class MessagesActivity : AppCompatActivity(), ItemLongClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MessagesViewModel

    private val messagesAdapter by lazy { MessagesAdapter(this, this) }

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

        overlayView.visibility = View.GONE
        actionContainer.visibility = View.GONE

        overlayView.setOnClickListener {
            hideActionView()
        }
    }

    override fun onAttachmentLongPress(position: Int, attachment: AttachmentViewEntity) {
        showDeleteAttachmentView(position, attachment)
    }

    override fun onMessageLongPress(position: Int, message: MessageViewEntity) {
        showDeleteMessageView(position, message)
    }

    private fun showMessages(messages: MessagesViewEntity) {
        messagesAdapter.updateMessages(messages)
    }

    private fun showDeleteMessageView(position: Int, message: MessageViewEntity) {
        showActionView("Delete message?")

        actionButton.setOnClickListener {
            viewModel.deleteMessage(message)
            hideActionView()
        }
    }

    private fun showDeleteAttachmentView(position: Int, attachment: AttachmentViewEntity) {
        showActionView("Delete attachment?")

        actionButton.setOnClickListener {
            viewModel.deleteAttachment(attachment)
            hideActionView()
        }
    }

    private fun showActionView(title: String) {
        with(overlayView) {
            alpha = 0f
            visibility = View.VISIBLE
            animate().alpha(1f)
                    .setDuration(200)
                    .withEndAction { }
        }

        with(actionContainer) {
            y = resources.displayMetrics.heightPixels + 300f
            visibility = View.VISIBLE
            animate().translationY(0f)
                    .setDuration(200)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .withEndAction { }
        }

        actionLabel.text = title
    }

    private fun hideActionView() {
        with(overlayView) {
            animate().alpha(0f)
                    .setDuration(200)
                    .withEndAction { this.visibility = View.GONE }
        }

        with(actionContainer) {
            animate().translationY(300f)
                    .setDuration(200)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .withEndAction { this.visibility = View.GONE }
        }
    }

    private fun showLoading() {
        // empty
    }

    private fun hideLoading() {
        // empty
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun hideError() {
        // empty
    }
}
