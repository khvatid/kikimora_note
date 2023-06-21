package khvatid.kikimora.data.source

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import khvatid.kikimora.data.store.datastore.AppDataStore
import khvatid.kikimora.data.store.datastore.source.AppApiPreferencesSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class AppApiPreferencesSourceImp(private val dataStore: AppDataStore) : AppApiPreferencesSource {
   override suspend fun setTokenForApi(apiKey: String, apiKeyPref: Preferences.Key<String>) {
      dataStore.invoke.edit {
         it[apiKeyPref] = apiKey
      }
   }

   override fun getTokenForApi(apiKeyPref: Preferences.Key<String>): Flow<String> =
      dataStore.invoke.data.catch {
         if (it is IOException) {
            it.printStackTrace()
            emit(emptyPreferences())
         } else {
            throw it
         }
      }.map {
         it[apiKeyPref] ?: ""
      }


}