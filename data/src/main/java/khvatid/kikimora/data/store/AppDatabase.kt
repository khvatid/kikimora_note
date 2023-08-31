package khvatid.kikimora.data.store

import androidx.room.Database
import androidx.room.RoomDatabase
import khvatid.kikimora.data.store.dao.NoteDao
import khvatid.kikimora.data.store.entities.ContentEntity
import khvatid.kikimora.data.store.entities.NoteEntity


@Database(
    version = 1,
    entities = [NoteEntity::class, ContentEntity::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNoteDao():NoteDao
}