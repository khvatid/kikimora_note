package khvatid.kikimora.features.conversations.list


import androidx.compose.runtime.Stable


interface ConversationListScreenContract {

    @Stable
    data class State(
        val listConversation: List<String>
    )

    sealed class Event{

    }


}