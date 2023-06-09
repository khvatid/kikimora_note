package khvatid.kikimora.features.settings

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import khvatid.kikimora.domain.usecase.GetIsDynamicThemeUseCase
import khvatid.kikimora.domain.usecase.SetApiTokenUseCase
import khvatid.kikimora.domain.usecase.SetIsDynamicThemeUseCase
import khvatid.kikimora.utils.ViewModelMVI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
   private val getIsDynamicThemeUseCase: GetIsDynamicThemeUseCase,
   private val setDynamicThemeUseCase: SetIsDynamicThemeUseCase,
   private val setApiTokenUseCase: SetApiTokenUseCase,
) : ViewModelMVI<SettingsScreenContract.State, SettingsScreenContract.Events>() {
   override val state: MutableStateFlow<SettingsScreenContract.State> = MutableStateFlow(
      SettingsScreenContract.State()
   )

   init {
      viewModelScope.launch(Dispatchers.IO) {
         getIsDynamicThemeUseCase.execute().collect {
            state.compareAndSet(state.value, state.value.copy(isDynamicTheme = it))
         }
      }
   }


   override fun obtainEvent(event: SettingsScreenContract.Events) {
      when (event) {
         is SettingsScreenContract.Events.OnTokenChanged -> {
            reduce(event)
         }

         is SettingsScreenContract.Events.UseDynamicTheme -> {
            reduce(event)
         }

         is SettingsScreenContract.Events.AcceptTokenChanged -> {
            reduce(event)
         }
      }
   }

   private fun reduce(event: SettingsScreenContract.Events.OnTokenChanged) {
      state.update { it.copy(token = event.value) }
   }

   private fun reduce(event: SettingsScreenContract.Events.AcceptTokenChanged) {
      viewModelScope.launch {
         setApiTokenUseCase.execute(state.value.token)
      }
   }

   private fun reduce(event: SettingsScreenContract.Events.UseDynamicTheme) {
      viewModelScope.launch {
         val value = !state.value.isDynamicTheme
         setDynamicThemeUseCase.execute(value)
         state.compareAndSet(state.value, state.value.copy(isDynamicTheme = value))
      }
   }


   override fun onCleared() {
      viewModelScope.cancel()
      Log.i("SETTINGS", "ViewModelScope is clear")
      super.onCleared()
   }

}