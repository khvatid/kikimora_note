package khvatid.kikimora.data.repository

import khvatid.kikimora.data.store.entities.ContentEntity
import khvatid.kikimora.data.store.entities.NoteEntity
import khvatid.kikimora.data.store.entities.NoteWithContent
import khvatid.kikimora.data.store.source.NotesSource
import khvatid.kikimora.note.domain.models.ContentModel
import khvatid.kikimora.note.domain.models.NoteModel
import khvatid.kikimora.note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImp(private val source: NotesSource) : NoteRepository {
    override fun getNote(id: Int): Flow<NoteModel> {
        return source.getNoteWithContent(id).map { it.toNoteModel() }
    }

    override suspend fun upsertNote(noteModel: NoteModel) {
        source.upsertNote(noteWithContent = noteModel.toNoteWithContent())
    }


    private fun NoteModel.toNoteWithContent(): NoteWithContent {
        return NoteWithContent(
            note = NoteEntity(
                id = this.id,
                title = this.title,
                author = "ADMIN",
                date = "08.09.2023",
            ),
            content = if (this.content != null) listOf(
                ContentEntity(
                    id = this.content?.id ?: 0,
                    noteId = this.id,
                    type = "text",
                    content = this.content?.text ?: ""
                )
            ) else emptyList()

        )
    }

    private fun NoteWithContent.toNoteModel(): NoteModel {
        val bdContent: ContentEntity? = this.content.firstOrNull()
        val content: ContentModel? = if (bdContent == null) null else
            ContentModel(
                id = bdContent.id,
                text = bdContent.content
            )
        return NoteModel(
            id = this.note.id,
            title = this.note.title,
            content = content
        )
    }
}