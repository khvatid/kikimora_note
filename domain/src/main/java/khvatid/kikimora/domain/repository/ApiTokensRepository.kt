package khvatid.kikimora.domain.repository

import khvatid.kikimora.domain.models.TokenModel
import kotlinx.coroutines.flow.Flow

interface ApiTokensRepository {
  fun getToken(apiProvider: TokenModel.ApiProvider):Flow<TokenModel.Token>
  suspend fun setToken(tokenModel: TokenModel.TokenWithProvider)
}