package khvatid.kikimora.features.conversation


import android.util.Log
import androidx.annotation.UiThread
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import khvatid.kikimora.domain.models.AnswerModel
import khvatid.kikimora.domain.models.MessageModel
import khvatid.kikimora.domain.usecase.GetLanguageModelResponseUseCase
import khvatid.kikimora.utils.ViewModelMVI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ConversationScreenViewModel @Inject constructor(
    private val getLanguageModelResponseUseCase: GetLanguageModelResponseUseCase
) : ViewModelMVI<ConversationScreenContract.State, ConversationScreenContract.Event>() {
    override val state: MutableStateFlow<ConversationScreenContract.State> = MutableStateFlow(
        ConversationScreenContract.State(
            message = "",
            messages = emptyList()
        )
    )

    override fun obtainEvent(event: ConversationScreenContract.Event) {
        when (event) {
            is ConversationScreenContract.Event.MessageValueChanged -> {
                reduce(event)
            }

            is ConversationScreenContract.Event.SendMessage -> {
                reduce(event)
            }
        }
    }

    private fun reduce(event: ConversationScreenContract.Event.MessageValueChanged) {
        state.compareAndSet(state.value, state.value.copy(message = event.value))
    }

    @UiThread
    private fun reduce(event: ConversationScreenContract.Event.SendMessage) {
        state.compareAndSet(
            state.value, state.value.copy(
                message = "",
                messages = uiState.value.messages + MessageModel(
                    role = "user",
                    content = state.value.message
                )
            )
        )

        val messages: List<MessageModel> = state.value.messages
        viewModelScope.launch(Dispatchers.IO) {
            getLanguageModelResponseUseCase.execute(
                messages.takeLast(5)
            ).collect { answer ->
                when (answer) {
                    is AnswerModel.Error -> {
                        state.compareAndSet(
                            state.value,
                            state.value.copy(
                                languageModelWrite = false,
                                messages = state.value.messages + MessageModel(
                                    content = "${answer.error} ",
                                    role = "system"
                                )
                            )
                        )
                    }

                    is AnswerModel.Loading -> state.compareAndSet(
                        state.value,
                        state.value.copy(languageModelWrite = true)
                    )

                    is AnswerModel.Success -> state.compareAndSet(
                        state.value,
                        state.value.copy(
                            languageModelWrite = false,
                            messages = state.value.messages + answer.message
                        )
                    )
                }
            }

            Log.i("HOME_SCREEN", "Collect is stoped")
        }
    }

}