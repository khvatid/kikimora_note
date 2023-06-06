package khvatid.androidAi.data.store.datastore.source

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface AppApiPreferencesSource {
  suspend fun setTokenForApi(apiKey: String, apiKeyPref: Preferences.Key<String>)
  fun getTokenForApi(apiKeyPref: Preferences.Key<String>): Flow<String>
}