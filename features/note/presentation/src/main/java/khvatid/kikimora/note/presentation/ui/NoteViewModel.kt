package khvatid.kikimora.note.presentation.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import khvatid.core.ui.components.ComposeViewModel
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
            is Events.OnContentTextChange -> reduce(event)
            is Events.OnTitleTextChange -> reduce(event)
            is Events.OnLaunch -> reduce(event)
            is Events.OnAccept -> {}
        }
    }

    private fun reduce(event: Events.OnTitleTextChange) {
        state.update { it.copy(title = event.value) }
    }

    private fun reduce(event: Events.OnContentTextChange) {
        state.update { it.copy(content = it.content.copy(text = event.value)) }
    }

    private fun reduce(event: Events.OnLaunch) {
        state.update {
            it
        }
    }
}