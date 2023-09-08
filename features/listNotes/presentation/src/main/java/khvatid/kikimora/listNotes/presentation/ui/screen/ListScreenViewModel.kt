package khvatid.kikimora.listNotes.presentation.ui.screen

import dagger.hilt.android.lifecycle.HiltViewModel
import khvatid.core.ui.components.ComposeViewModel
import khvatid.kikimora.listNotes.presentation.ui.screen.ListScreenContract.*
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor() : ComposeViewModel<State, Events>() {
    override val state: MutableStateFlow<State> = MutableStateFlow(State())

    override fun obtainEvent(event: Events) {
        when(event){
            else -> {}
        }
    }
}