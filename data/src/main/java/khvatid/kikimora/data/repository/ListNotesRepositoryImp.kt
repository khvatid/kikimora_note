package khvatid.kikimora.data.repository

import khvatid.kikimora.data.store.entities.NoteEntity
import khvatid.kikimora.data.store.source.NotesSource
import khvatid.kikimora.listNotes.domain.model.NoteModel
import khvatid.kikimora.listNotes.domain.repository.ListNotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ListNotesRepositoryImp(private val source: NotesSource): ListNotesRepository {
    override fun getListNotes(): Flow<List<NoteModel>> {
        return source.getNotes().map {list-> list.map { it.toNoteModel() }}
    }

    override fun deleteNote(id: Int) {

    }


    private fun NoteEntity.toNoteModel():NoteModel{
        return NoteModel(
            id, title, author, date
        )
    }

}