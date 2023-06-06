package khvatid.androidAi.data.store.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

class AppDataStore(private val context: Context) {

   val invoke get() = context.dataStore
   companion object {
      private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
         name = "Ai_app_preferences"
      )
   }
}
