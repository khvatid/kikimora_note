package khvatid.kikimora.data.store.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import khvatid.kikimora.data.store.entities.ContentEntity
import khvatid.kikimora.data.store.entities.NoteEntity
import khvatid.kikimora.data.store.entities.NoteWithContent
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query(value = "SELECT * FROM note_table")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query(value = "SELECT * FROM note_table WHERE id = :id")
    fun getNoteById(id: Int): Flow<NoteEntity>

    @Query("SELECT note_table.id AS noteId, title, content_table.id, type, content FROM note_table LEFT JOIN content_table ON note_table.id = content_table.noteId WHERE note_table.id = :id")
    fun getNoteWithContent(id: Int): Flow<NoteWithContent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertNote(noteEntity: NoteEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertContent(contentEntity: ContentEntity)

    // fun deleteContent(contentEntity: ContentEntity)
    // fun deleteNote(noteEntity: NoteEntity)

}