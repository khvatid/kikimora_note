package khvatid.kikimora.features.conversation

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Stable
import khvatid.kikimora.domain.models.MessageModel

interface ConversationScreenContract {

    @Stable
    data class State(
        val listState: LazyListState = LazyListState(),
        val languageModelWrite: Boolean = false,
        val message: String,
        val messages: List<MessageModel>
    )

    sealed class Event{
        data class MessageValueChanged(val value: String): Event()
        object SendMessage : Event()
    }


}