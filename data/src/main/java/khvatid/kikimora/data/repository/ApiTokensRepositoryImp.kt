package khvatid.kikimora.data.repository

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import khvatid.kikimora.data.store.datastore.source.AppApiPreferencesSource
import khvatid.kikimora.domain.models.TokenModel
import khvatid.kikimora.domain.repository.ApiTokensRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ApiTokensRepositoryImp(
   private val apiPreferencesSource: AppApiPreferencesSource
) : ApiTokensRepository {
   override fun getToken(apiProvider: TokenModel.ApiProvider): Flow<TokenModel.Token> =
      apiPreferencesSource.getTokenForApi(apiProvider.asPrefKey()).map { TokenModel.Token(it) }

   override suspend fun setToken(tokenModel: TokenModel.TokenWithProvider) {
      apiPreferencesSource.setTokenForApi(
         apiKey = tokenModel.token,
         apiKeyPref = tokenModel.apiProvider.asPrefKey()
      )
   }

   private fun TokenModel.ApiProvider.asPrefKey(): Preferences.Key<String> {
      return when (this) {
         is TokenModel.ApiProvider.OpenAiApi -> OPEN_AI_TOKEN_KEY
      }
   }

   private val OPEN_AI_TOKEN_KEY = stringPreferencesKey("open_ai_token")
}