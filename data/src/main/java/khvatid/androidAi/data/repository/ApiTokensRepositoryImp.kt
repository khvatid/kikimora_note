package khvatid.androidAi.data.repository

import khvatid.androidAi.data.store.datastore.source.AppApiPreferencesSource
import khvatid.androidAi.domain.models.TokenModel
import khvatid.androidAi.domain.repository.ApiTokensRepository

class ApiTokensRepositoryImp(
  private val apiPreferencesSource: AppApiPreferencesSource
): ApiTokensRepository {
  override fun getOpenAiToken(): TokenModel {
    TODO("Not yet implemented")
  }

  override fun setOpenAiToken(tokenModel: TokenModel) {
    TODO("Not yet implemented")
  }
}