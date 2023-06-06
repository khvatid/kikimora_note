package khvatid.androidAi.domain.usecase

import khvatid.androidAi.domain.models.AnswerModel
import khvatid.androidAi.domain.models.MessageModel
import khvatid.androidAi.domain.models.TokenModel
import khvatid.androidAi.domain.repository.ApiTokensRepository
import khvatid.androidAi.domain.repository.ConversationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transformWhile
import kotlinx.coroutines.launch

class GetLanguageModelResponseUseCase(
   private val repository: ConversationRepository,
   private val apiTokensRepository: ApiTokensRepository
) {
    init {
        CoroutineScope(Dispatchers.IO).launch {
            apiTokensRepository.getToken(TokenModel.ApiProvider.OpenAiApi).collect(){
                   token = it.token
            }
        }
    }
   private var token : String = ""

   suspend fun execute(messages: List<MessageModel>): Flow<AnswerModel> {
      return repository.getAnswer(messages, token = token).transformWhile {
         emit(it)
         when (it) {
            is AnswerModel.Error -> false
            is AnswerModel.Loading -> true
            is AnswerModel.Success -> false
         }
      }
   }
}