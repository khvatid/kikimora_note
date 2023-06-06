package khvatid.androidAi.data.repository

import khvatid.androidAi.data.store.datastore.source.AppSettingPreferencesSource
import khvatid.androidAi.domain.repository.AppSettingRepository
import kotlinx.coroutines.flow.Flow

class AppSettingRepositoryImp(private val settingPreferencesSource: AppSettingPreferencesSource) :
  AppSettingRepository {
  override fun getIsDynamicTheme(): Flow<Boolean> = settingPreferencesSource.getIsDynamicTheme()

  override suspend fun setIsDynamicTheme(value: Boolean) {
    settingPreferencesSource.setIsDynamicTheme(value)
  }
}