package khvatid.kikimora.data.store.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
data class NoteEntity(
    @PrimaryKey
    val id : Int,
    val title: String,

)

@Entity(tableName = "content_table",
    foreignKeys = [ForeignKey(
        entity = NoteEntity::class,
        parentColumns = ["id"],
        childColumns = ["noteId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.NO_ACTION
    )])
data class ContentEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val noteId: Int,
    val type: String,
    val content: String
)


data class NoteWithContent(
    @Embedded val note : NoteEntity,
    val content: List<ContentEntity>
)


/*
data class Note(
    val id : Int,
    val title: String,
    val content: List<Content>
){
    sealed class Content{
        data class Audio(val id: Int, val path: String): Content()
        data class Text(val id:Int, val text: String): Content()
        data class Photo(val id: Int, val photoPath: String): Content()
    }
}*/
