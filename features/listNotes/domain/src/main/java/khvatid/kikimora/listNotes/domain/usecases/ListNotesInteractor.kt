package khvatid.kikimora.listNotes.domain.usecases

import khvatid.kikimora.listNotes.domain.model.NoteModel
import kotlinx.coroutines.flow.Flow

interface ListNotesInteractor {

    fun getListNotes(): Flow<List<NoteModel>>

    fun deleteNote(noteModel: NoteModel)


}