package khvatid.kikimora.note.presentation.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import khvatid.core.ui.components.ComposeViewModel
import khvatid.kikimora.note.domain.models.ContentModel
import khvatid.kikimora.note.presentation.ui.NoteScreenContract.Events
import khvatid.kikimora.note.presentation.ui.NoteScreenContract.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor() : ComposeViewModel<State, Events>() {
    override val state: MutableStateFlow<State> = MutableStateFlow(State())

    override fun obtainEvent(event: Events) {
        when (event) {
            is Events.AddContent -> reduce(event)
            is Events.ChangeContentText -> reduce(event)
            is Events.DeleteContent -> reduce(event)
            else -> {}
        }
    }

    private fun reduce(event: Events.AddContent) {
        state.update { it.copy(content = it.content + event.content) }
    }

    private fun reduce(event: Events.ChangeContentText) {
        state.update {
            val list: MutableList<ContentModel> = it.content.toMutableList()
            list[event.contentIndex] = event.value
            it.copy(content = list)
        }
    }

    private fun reduce(event: Events.DeleteContent) {
        state.update {
            it.copy(content = it.content.apply {
                this.toMutableList().removeAt(event.contentIndex)
            })
        }
    }
}