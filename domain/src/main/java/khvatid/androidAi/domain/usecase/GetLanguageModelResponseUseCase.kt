package khvatid.androidAi.domain.usecase

import khvatid.androidAi.domain.models.AnswerModel
import khvatid.androidAi.domain.models.MessageModel
import khvatid.androidAi.domain.repository.ConversationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transformWhile

class GetLanguageModelResponseUseCase(private val repository: ConversationRepository) {

    suspend fun execute(messages: List<MessageModel>): Flow<AnswerModel> {
        return repository.getAnswer(messages).transformWhile {
            emit(it)
            when (it) {
                is AnswerModel.Error -> false
                is AnswerModel.Loading -> true
                is AnswerModel.Success -> false
            }
        }
    }
}