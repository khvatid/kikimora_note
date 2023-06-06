package khvatid.androidAi.domain.repository

import khvatid.androidAi.domain.models.TokenModel

interface ApiTokensRepository {
  fun getOpenAiToken():TokenModel
  fun setOpenAiToken(tokenModel: TokenModel)
}