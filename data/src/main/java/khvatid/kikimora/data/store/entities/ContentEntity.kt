package khvatid.kikimora.data.store.entities

import androidx.room.Entity


@Entity(tableName = "content_table")
data class ContentEntity(
    val id : Int,
    val idNote: Int,
    val type: String,
    val content: String
)