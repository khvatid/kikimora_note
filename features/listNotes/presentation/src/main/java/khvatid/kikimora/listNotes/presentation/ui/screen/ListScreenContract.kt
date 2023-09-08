package khvatid.kikimora.listNotes.presentation.ui.screen

import khvatid.kikimora.listNotes.domain.model.NoteModel

interface ListScreenContract {

    data class State(
        val notes: List<NoteModel> = emptyList()
    )

    sealed class Events{




    }

}