package khvatid.kikimora.utils


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow





abstract class ViewModelMVI<STATE, EVENT> : ViewModel() {

    protected abstract val state: MutableStateFlow<STATE>
    val uiState: StateFlow<STATE> get() = state
    abstract fun obtainEvent(event: EVENT)
}


