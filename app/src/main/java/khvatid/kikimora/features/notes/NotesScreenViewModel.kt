package khvatid.kikimora.features.notes


import dagger.hilt.android.lifecycle.HiltViewModel
import khvatid.kikimora.utils.ViewModelMVI
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class NotesScreenViewModel @Inject constructor(
) : ViewModelMVI<NotesScreenContract.State, NotesScreenContract.Event>() {
  override val state: MutableStateFlow<NotesScreenContract.State> = MutableStateFlow(
    NotesScreenContract.State()
  )

  override fun obtainEvent(event: NotesScreenContract.Event) {
    when (event) {
      else -> {}
    }
  }
}