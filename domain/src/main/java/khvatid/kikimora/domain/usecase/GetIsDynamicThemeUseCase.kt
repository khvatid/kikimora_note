package khvatid.kikimora.domain.usecase

import khvatid.kikimora.domain.repository.AppSettingRepository
import kotlinx.coroutines.flow.Flow

class GetIsDynamicThemeUseCase(private val settingRepository: AppSettingRepository) {

    fun execute(): Flow<Boolean> = settingRepository.getIsDynamicTheme()

}