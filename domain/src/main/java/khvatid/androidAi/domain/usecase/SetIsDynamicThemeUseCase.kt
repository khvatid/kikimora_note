package khvatid.androidAi.domain.usecase

import khvatid.androidAi.domain.repository.AppSettingRepository

class SetIsDynamicThemeUseCase(private val settingRepository: AppSettingRepository) {

    suspend fun execute(value: Boolean) {
        settingRepository.setIsDynamicTheme(value = value)
    }

}