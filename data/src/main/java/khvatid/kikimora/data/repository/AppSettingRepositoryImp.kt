package khvatid.kikimora.data.repository

import khvatid.kikimora.data.store.datastore.source.AppSettingPreferencesSource
import khvatid.kikimora.domain.repository.AppSettingRepository
import kotlinx.coroutines.flow.Flow

class AppSettingRepositoryImp(private val settingPreferencesSource: AppSettingPreferencesSource) :
  AppSettingRepository {
  override fun getIsDynamicTheme(): Flow<Boolean> = settingPreferencesSource.getIsDynamicTheme()

  override suspend fun setIsDynamicTheme(value: Boolean) {
    settingPreferencesSource.setIsDynamicTheme(value)
  }
}