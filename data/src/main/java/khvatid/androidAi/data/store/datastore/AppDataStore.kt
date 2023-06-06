package khvatid.androidAi.data.store.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class AppDataStore(private val context: Context) {

   val invoke get() = context.dataStore

   /* suspend fun saveDynamicThemeToPreferencesStore(isDynamicTheme: Boolean){
        context.dataStore.edit {
            it[IS_DYNAMIC_THEME] = isDynamicTheme
        }
    }

    val preferencesFlow: Flow<Boolean> = context.dataStore.data.catch {
        if (it is IOException){
            it.printStackTrace()
            emit(emptyPreferences())
        }else{
            throw it
        }
    }.map {
        it[IS_DYNAMIC_THEME]?:false
    }*/
   companion object {
      private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
         name = "Ai_app_preferences"
      )
      // private val IS_DYNAMIC_THEME = booleanPreferencesKey("is_dynamic_theme")
   }
}
