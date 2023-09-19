package khvatid.kikimora.note.domain.repository

import khvatid.kikimora.note.domain.models.NoteModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNote(id : Int): Flow<NoteModel>
    suspend fun upsertNote(noteModel: NoteModel)

}