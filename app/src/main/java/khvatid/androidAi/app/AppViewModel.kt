package khvatid.androidAi.app


import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import khvatid.androidAi.domain.usecase.GetIsDynamicThemeUseCase
import khvatid.androidAi.utils.ViewModelMVI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    navHostController: NavHostController,
    private val getIsDynamicThemeUseCase: GetIsDynamicThemeUseCase
) : ViewModelMVI<AppContract.State, AppContract.Event>() {


    override val state: MutableStateFlow<AppContract.State> =
        MutableStateFlow(AppContract.State(navController = navHostController))

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getIsDynamicThemeUseCase.execute().collect {
                state.compareAndSet(state.value, state.value.copy(isDynamicTheme = it))
            }
        }
    }

    override fun obtainEvent(event: AppContract.Event) {
        when (event) {
            is AppContract.Event.NavigateToHome -> TODO()
            is AppContract.Event.NavigateToSettings -> {
                reduce(event)
            }
        }
    }

    private fun reduce(event: AppContract.Event.NavigateToSettings) {
        state.value.navController.navigate("settings")
        //state.compareAndSet(state.value, state.value.copy(showAppBar = false))
    }

}