package khvatid.kikimora.ui

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    navController: NavHostController
) : ViewModel() {

    private val _state: MutableStateFlow<AppScreenState> =
        MutableStateFlow(AppScreenState(navController))
    val state: StateFlow<AppScreenState> get() = _state


}