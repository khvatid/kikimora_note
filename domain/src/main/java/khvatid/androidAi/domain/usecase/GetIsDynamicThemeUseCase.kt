package khvatid.androidAi.domain.usecase

import khvatid.androidAi.domain.repository.AppSettingRepository
import kotlinx.coroutines.flow.Flow

class GetIsDynamicThemeUseCase(private val settingRepository: AppSettingRepository) {

    fun execute(): Flow<Boolean> = settingRepository.getIsDynamicTheme()

}