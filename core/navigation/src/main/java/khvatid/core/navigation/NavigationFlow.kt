package khvatid.core.navigation

sealed class NavigationFlow(private val route: String) {

    object Notes : NavigationFlow(route = "notes_flow")
    object Settings : NavigationFlow(route = "settings_flow")

    operator fun invoke(): String {
        return route
    }
}