package khvatid.androidAi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import khvatid.androidAi.domain.repository.ApiTokensRepository
import khvatid.androidAi.domain.repository.AppSettingRepository
import khvatid.androidAi.domain.repository.ConversationRepository
import khvatid.androidAi.domain.usecase.GetIsDynamicThemeUseCase
import khvatid.androidAi.domain.usecase.GetLanguageModelResponseUseCase
import khvatid.androidAi.domain.usecase.SetApiTokenUseCase
import khvatid.androidAi.domain.usecase.SetIsDynamicThemeUseCase


@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

   @Provides
   fun provideGetLanguageModelResponse(
      conversationRepository: ConversationRepository,
      apiTokensRepository: ApiTokensRepository
   ): GetLanguageModelResponseUseCase {
      return GetLanguageModelResponseUseCase(
         repository = conversationRepository,
         apiTokensRepository = apiTokensRepository
      )
   }

   @Provides
   fun provideSetApiTokenUseCase(apiTokensRepository: ApiTokensRepository): SetApiTokenUseCase {
      return SetApiTokenUseCase(apiTokensRepository = apiTokensRepository)
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