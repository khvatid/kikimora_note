package khvatid.kikimora.note.domain.models

sealed class ContentModel {
    data class Text(val text: String) : ContentModel()

    data class Photo(val path : String) : ContentModel()
}