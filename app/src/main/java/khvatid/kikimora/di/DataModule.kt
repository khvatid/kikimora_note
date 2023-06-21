package khvatid.kikimora.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import khvatid.kikimora.data.repository.ApiTokensRepositoryImp
import khvatid.kikimora.data.repository.AppSettingRepositoryImp
import khvatid.kikimora.data.repository.ConversationRepositoryImp
import khvatid.kikimora.data.source.AppApiPreferencesSourceImp
import khvatid.kikimora.data.source.AppSettingPreferencesSourceImp
import khvatid.kikimora.data.source.OpenAiSourceImp
import khvatid.kikimora.data.store.datastore.AppDataStore
import khvatid.kikimora.data.store.datastore.source.AppApiPreferencesSource
import khvatid.kikimora.data.store.datastore.source.AppSettingPreferencesSource
import khvatid.kikimora.data.store.retrofit.NetworkInstance
import khvatid.kikimora.data.store.retrofit.source.OpenAiSource
import khvatid.kikimora.domain.repository.ApiTokensRepository
import khvatid.kikimora.domain.repository.AppSettingRepository
import khvatid.kikimora.domain.repository.ConversationRepository

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