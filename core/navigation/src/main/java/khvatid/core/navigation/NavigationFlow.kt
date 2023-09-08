package khvatid.core.navigation

sealed class NavigationFlow(private val route: String) {

    data class Note(val id: String) : NavigationFlow(route = "note_flow/$id")
    object ListNotes : NavigationFlow(route = "listNotes_flow")
    object Settings : NavigationFlow(route = "settings_flow")

    operator fun invoke(): String {
        return route
    }
}