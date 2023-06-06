package khvatid.androidAi.data.store.datastore.source

import androidx.datastore.preferences.core.Preferences

interface AppApiPreferencesSource {
  suspend fun setTokenForApi(apiKey: String, apiKeyPref: Preferences.Key<String>)
  suspend fun getTokenForApi(apiKeyPref: Preferences.Key<String>): String
}