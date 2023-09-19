package khvatid.kikimora.data.store.source

import khvatid.kikimora.data.store.dao.NoteDao
import khvatid.kikimora.data.store.entities.NoteEntity
import khvatid.kikimora.data.store.entities.NoteWithContent
import kotlinx.coroutines.flow.Flow

class NotesSource(private val dao: NoteDao) {

    fun getNotes(): Flow<List<NoteEntity>> {
        return dao.getNotes()
    }

    fun getNoteWithContent(id: Int): Flow<NoteWithContent> {
        return dao.getNoteWithContent(id)
    }

    suspend fun upsertNote(noteWithContent: NoteWithContent) {
        val noteId = dao.upsertNote(noteWithContent.note)
        noteWithContent.content.forEach {
            dao.upsertContent(it.copy(noteId = noteId.toInt()))
        }
    }


}