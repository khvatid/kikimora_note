package khvatid.kikimora.features.app


import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import khvatid.kikimora.domain.usecase.GetIsDynamicThemeUseCase
import khvatid.kikimora.features.app.navigation.AppDestination
import khvatid.kikimora.utils.ViewModelMVI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
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
                state.update { appState ->
                    appState.copy(isDynamicTheme = it)
                }
            }
        }
    }

    override fun obtainEvent(event: AppContract.Event) {
        when (event) {
            is AppContract.Event.NavigateToConversation -> {
                reduce(event)
            }

            is AppContract.Event.BottomBarClick -> {
                reduce(event)
            }
        }
    }

    private fun reduce(event: AppContract.Event.BottomBarClick) {
        if (state.value.route != event.route){
            state.value.bottomBarNavigate(event.route)
        }
    }

    private fun reduce(event: AppContract.Event.NavigateToConversation) {
        state.value.navController.navigate(AppDestination.Conversation(event.id).fullRoute)
    }

    private fun AppContract.State.bottomBarNavigate(route: String){
        this.navController.navigate(
            route = route
        ){
            launchSingleTop = true
            popUpTo(0) {
                inclusive = true
            }
        }
    }

}