package khvatid.kikimora.listNotes.presentation.ui.screens.list

import khvatid.kikimora.listNotes.domain.model.NoteModel

interface ListScreenContract {

    data class State(
        val notes: List<NoteModel> = emptyList()
    )

    sealed class Events{




    }

}