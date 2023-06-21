package khvatid.kikimora.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import khvatid.kikimora.domain.repository.ApiTokensRepository
import khvatid.kikimora.domain.repository.AppSettingRepository
import khvatid.kikimora.domain.repository.ConversationRepository
import khvatid.kikimora.domain.usecase.GetIsDynamicThemeUseCase
import khvatid.kikimora.domain.usecase.GetLanguageModelResponseUseCase
import khvatid.kikimora.domain.usecase.SetApiTokenUseCase
import khvatid.kikimora.domain.usecase.SetIsDynamicThemeUseCase


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