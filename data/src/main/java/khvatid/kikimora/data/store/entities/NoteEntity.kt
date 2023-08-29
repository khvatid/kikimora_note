package khvatid.kikimora.data.store.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
data class NoteEntity(
    @PrimaryKey
    val id : Int,
    val title: String,

)