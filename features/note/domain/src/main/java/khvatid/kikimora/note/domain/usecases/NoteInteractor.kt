package khvatid.kikimora.note.domain.usecases

import khvatid.kikimora.note.domain.models.NoteModel
import kotlinx.coroutines.flow.Flow

interface NoteInteractor {

    fun getNote(id:Int): Flow<NoteModel>

    fun upsertNote(noteModel: NoteModel)

    fun deleteNote(noteModel: NoteModel)

}