package khvatid.kikimora.listNotes.domain.repository

import khvatid.kikimora.listNotes.domain.model.NoteModel
import kotlinx.coroutines.flow.Flow

interface ListNotesRepository {
    fun getListNotes(): Flow<List<NoteModel>>
    fun deleteNote(id: Int)

}