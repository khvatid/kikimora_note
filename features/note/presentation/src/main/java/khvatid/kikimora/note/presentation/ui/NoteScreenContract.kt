package khvatid.kikimora.note.presentation.ui

import khvatid.kikimora.note.domain.models.ContentModel
import khvatid.kikimora.note.domain.models.NoteModel


interface NoteScreenContract {

    data class State(
        val id: Int = 0,
        val title: String = "",
        val content: ContentModel = ContentModel(),
        ) {
        class Builder {
            fun buildFromNoteModel(noteModel: NoteModel): State {
                return State(
                    id = noteModel.id,
                    title = noteModel.title,
                    content = noteModel.content?: ContentModel()
                )
            }
        }
    }

    sealed class Events {
        data class OnLaunch(val id: Int): Events()
        data class OnContentTextChange(val value: String): Events()
        data class OnTitleTextChange(val value: String): Events()
        object OnAccept: Events()


    }

}