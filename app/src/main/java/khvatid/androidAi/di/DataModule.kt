package khvatid.androidAi.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import khvatid.androidAi.data.repository.ApiTokensRepositoryImp
import khvatid.androidAi.data.repository.AppSettingRepositoryImp
import khvatid.androidAi.data.repository.ConversationRepositoryImp
import khvatid.androidAi.data.source.AppApiPreferencesSourceImp
import khvatid.androidAi.data.source.AppSettingPreferencesSourceImp
import khvatid.androidAi.data.source.OpenAiSourceImp
import khvatid.androidAi.data.store.datastore.AppDataStore
import khvatid.androidAi.data.store.datastore.source.AppApiPreferencesSource
import khvatid.androidAi.data.store.datastore.source.AppSettingPreferencesSource
import khvatid.androidAi.data.store.retrofit.NetworkInstance
import khvatid.androidAi.data.store.retrofit.source.OpenAiSource
import khvatid.androidAi.domain.repository.ApiTokensRepository
import khvatid.androidAi.domain.repository.AppSettingRepository
import khvatid.androidAi.domain.repository.ConversationRepository

@Module
@InstallIn(SingletonComponent::class)
class DataModule {


   @Provides
   fun provideAppDataStore(@ApplicationContext context: Context): AppDataStore {
      return AppDataStore(context)
   }

   @Provides
   fun provideAppApiPreferencesSource(dataStore: AppDataStore):AppApiPreferencesSource{
      return AppApiPreferencesSourceImp(dataStore = dataStore)
   }


   @Provides
   fun provideApiTokensRepository(apiPreferencesSource: AppApiPreferencesSource): ApiTokensRepository {
      return ApiTokensRepositoryImp(apiPreferencesSource = apiPreferencesSource)
   }

   @Provides
   fun provideAppSettingsPreferencesSource(dataStore: AppDataStore): AppSettingPreferencesSource {
      return AppSettingPreferencesSourceImp(dataStore = dataStore)
   }


   @Provides
   fun provideAppSettingRepository(settingPreferencesSource: AppSettingPreferencesSource): AppSettingRepository {
      return AppSettingRepositoryImp(settingPreferencesSource = settingPreferencesSource)
   }


   @Provides
   fun provideNetworkInstance(): NetworkInstance {
      return NetworkInstance()
   }


   @Provides
   fun provideOpenAiSource(network: NetworkInstance): OpenAiSource {
      return OpenAiSourceImp(network = network)
   }

   @Provides
   fun provideConversationRepository(
      source: OpenAiSource
   ): ConversationRepository {
      return ConversationRepositoryImp(openAiSource = source)
   }

}