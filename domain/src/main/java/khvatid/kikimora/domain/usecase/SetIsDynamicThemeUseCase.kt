package khvatid.kikimora.domain.usecase

import khvatid.kikimora.domain.repository.AppSettingRepository

class SetIsDynamicThemeUseCase(private val settingRepository: AppSettingRepository) {

    suspend fun execute(value: Boolean) {
        settingRepository.setIsDynamicTheme(value = value)
    }

}