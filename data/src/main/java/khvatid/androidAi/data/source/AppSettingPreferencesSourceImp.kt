package khvatid.androidAi.data.source

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import khvatid.androidAi.data.store.datastore.AppDataStore
import khvatid.androidAi.data.store.datastore.source.AppSettingPreferencesSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class AppSettingPreferencesSourceImp(private val dataStore: AppDataStore) :
   AppSettingPreferencesSource {
   override suspend fun setIsDynamicTheme(value: Boolean) {
     dataStore.invoke.edit {
       it[isDynamicThemePrefKey] = value
     }
   }

   override fun getIsDynamicTheme(): Flow<Boolean> = dataStore.invoke.data.catch {
      if (it is IOException) {
         it.printStackTrace()
         emit(emptyPreferences())
      } else {
         throw it
      }
   }.map {
      it[isDynamicThemePrefKey] ?: false
   }

   private val isDynamicThemePrefKey = booleanPreferencesKey("is_dynamic_theme")
}