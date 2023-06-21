package khvatid.kikimora.domain.usecase

import khvatid.kikimora.domain.models.TokenModel
import khvatid.kikimora.domain.repository.ApiTokensRepository

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