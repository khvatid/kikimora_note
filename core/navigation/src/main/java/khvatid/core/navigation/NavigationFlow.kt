package ru.khvatid.core.navigation

sealed class NavigationFlow(protected val route: String) {

    val fullRoute: String = route

    sealed class NoArgsDestination(
        route: String
    ) : NavigationFlow(route) {
        operator fun invoke(): String = route
    }


    sealed class NotesNavigation() {
        object List : NoArgsDestination(route = "list")
        data class Detail(private val id: String = "{id}") :
            NavigationFlow(route = "${List()}/$id")

        companion object {
            const val graphName: String = "notes"
        }
    }

}