package khvatid.androidAi.data.store.datastore.source

import kotlinx.coroutines.flow.Flow

interface AppSettingPreferencesSource {
  suspend fun setIsDynamicTheme(value: Boolean)
  fun getIsDynamicTheme(): Flow<Boolean>
}