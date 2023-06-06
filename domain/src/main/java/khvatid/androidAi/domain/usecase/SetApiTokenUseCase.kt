package khvatid.androidAi.domain.usecase

import khvatid.androidAi.domain.models.TokenModel
import khvatid.androidAi.domain.repository.ApiTokensRepository

class SetApiTokenUseCase(private val apiTokensRepository: ApiTokensRepository) {
   suspend fun execute(value: String) {
      apiTokensRepository.setToken(
         tokenModel = TokenModel.TokenWithProvider(
            token = value,
            apiProvider = TokenModel.ApiProvider.OpenAiApi
         )
      )
   }
}