package khvatid.kikimora.features.app.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import khvatid.kikimora.R


sealed class AppDestination(protected val route:String) {
   val fullRoute: String = route
   sealed class NoArgumentsDestination(
      route: String,
      @DrawableRes val icon : Int = R.drawable.bot_ic,
      @StringRes val label : Int = R.string.app_name
   ): AppDestination(route){
      operator fun invoke():String = route
   }

   object Settings: NoArgumentsDestination(route = "settings")
   object ListConversations: NoArgumentsDestination(route = "conversations")
   object ListNotes: NoArgumentsDestination(route = "notes")
   data class Note(val id: String): AppDestination(route = "${ListNotes()}/id=$id")

   data class Conversation(private val id: String = "0"): AppDestination(route = "${ListConversations()}/id=$id")
}