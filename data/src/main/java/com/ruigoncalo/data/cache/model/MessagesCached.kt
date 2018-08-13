package com.ruigoncalo.data.cache.model

data class MessagesCached(val messages: List<MessageCached>,
                          val users: List<UserCached>)