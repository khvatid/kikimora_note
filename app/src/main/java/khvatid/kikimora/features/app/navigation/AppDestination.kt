package khvatid.kikimora.features.app.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import khvatid.kikimora.R


sealed class AppDestination(protected val route: String) {
    val fullRoute: String = route

    sealed class NoArgumentsDestination(
        route: String,
        @DrawableRes val icon: Int = R.drawable.bot_ic,
        @StringRes val label: Int = R.string.app_name
    ) : AppDestination(route) {
        operator fun invoke(): String = route
    }

    object Settings : NoArgumentsDestination(
        route = "settings",
        label = R.string.destination_settings_label,
        icon = R.drawable.ic_settings
    )

    object ConversationList : NoArgumentsDestination(
        route = "conversations",
        label = R.string.destination_conversations_label,
        icon = R.drawable.ic_conversation
    )

    data class Conversation(private val id: String = "0") :
        AppDestination(route = "${ConversationList()}/id=$id")

    object NoteList : NoArgumentsDestination(
        route = "notes",
        label = R.string.destination_notes_label,
        icon = R.drawable.ic_note
    )

    data class Note(val id: String) : AppDestination(route = "${NoteList()}/id=$id")
}