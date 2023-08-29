package khvatid.core.ui.components

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class ComposeViewModel<STATE, EVENT> : ViewModel() {


    protected abstract val state: MutableStateFlow<STATE>
    val uiState: StateFlow<STATE> get() = state

    abstract fun obtainEvent(event: EVENT)



}

