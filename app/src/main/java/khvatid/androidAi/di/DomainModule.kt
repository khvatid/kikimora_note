package khvatid.androidAi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import khvatid.androidAi.domain.repository.AppSettingRepository
import khvatid.androidAi.domain.repository.ConversationRepository
import khvatid.androidAi.domain.usecase.GetIsDynamicThemeUseCase
import khvatid.androidAi.domain.usecase.GetLanguageModelResponseUseCase
import khvatid.androidAi.domain.usecase.SetIsDynamicThemeUseCase


@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetLanguageModelResponse(repository: ConversationRepository): GetLanguageModelResponseUseCase {
        return GetLanguageModelResponseUseCase(repository = repository)
    }

    @Provides
    fun provideGetIsDynamicThemeUseCase(repository: AppSettingRepository): GetIsDynamicThemeUseCase {
        return GetIsDynamicThemeUseCase(repository)
    }

    @Provides
    fun provideSetIsDynamicThemeUseCase(repository: AppSettingRepository): SetIsDynamicThemeUseCase {
        return SetIsDynamicThemeUseCase(repository)
    }

}