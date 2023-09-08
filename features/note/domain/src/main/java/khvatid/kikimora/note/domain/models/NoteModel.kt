package khvatid.kikimora.note.domain.models

data class NoteModel(
    val id : Int = 0,
    val title : String = "",
    val content : ContentModel? = null
)
