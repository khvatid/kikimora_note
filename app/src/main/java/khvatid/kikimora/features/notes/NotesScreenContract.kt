package khvatid.kikimora.features.notes

import androidx.compose.runtime.Stable

interface NotesScreenContract {

    @Stable
    data class State(
        val notes: List<String> = emptyList()
    )

    sealed class Event{

    }


}