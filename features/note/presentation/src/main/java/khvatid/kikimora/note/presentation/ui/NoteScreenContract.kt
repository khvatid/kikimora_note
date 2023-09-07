package khvatid.kikimora.note.presentation.ui

import khvatid.kikimora.note.domain.models.ContentModel
import khvatid.kikimora.note.domain.models.NoteModel


interface NoteScreenContract {

    data class State(
        val id: Int = 0,
        val title: String = "",
        val content: List<ContentModel> = listOf(
            ContentModel.Text(""),
            ContentModel.Text(""),
            ContentModel.Text(""),
            ContentModel.Text(""),
            ContentModel.Text(""),
            ContentModel.Photo("https://images.unsplash.com/photo-1693592992203-67776cd88e8b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1974&q=80"),
            ContentModel.Text(""),
            ContentModel.Text(""),
            ContentModel.Photo("https://images.unsplash.com/photo-1691732183949-821035f00a78?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1974&q=80"),
            ContentModel.Text(""),
            ContentModel.Text("")
        ),

        ) {

        class Builder {
            fun buildFromNoteModel(noteModel: NoteModel): State {
                return State(
                    id = noteModel.id,
                    title = noteModel.title,
                    content = noteModel.content
                )
            }
        }
    }

    sealed class Events {
        data class ChangeContentText(val value: ContentModel.Text, val contentIndex: Int) : Events()
        data class AddContent(val content: ContentModel) : Events()
        data class DeleteContent(val contentIndex: Int) : Events()


    }

}