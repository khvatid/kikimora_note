package khvatid.core.ui.components

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.khvatid.core.navigation.NavigationFlow
import javax.inject.Inject

abstract class ComposeViewModel<STATE, EVENT> : ViewModel() {


    protected abstract val state: MutableStateFlow<STATE>
    val uiState: StateFlow<STATE> get() = state

    abstract fun obtainEvent(event: EVENT)



}

