package khvatid.kikimora.features.conversations.list



import dagger.hilt.android.lifecycle.HiltViewModel
import khvatid.kikimora.utils.ViewModelMVI
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class ConversationListScreenViewModel @Inject constructor(
) : ViewModelMVI<ConversationListScreenContract.State, ConversationListScreenContract.Event>() {
    override val state: MutableStateFlow<ConversationListScreenContract.State> = MutableStateFlow(
        ConversationListScreenContract.State(
           listConversation = emptyList()
        )
    )

    override fun obtainEvent(event: ConversationListScreenContract.Event) {

    }


}