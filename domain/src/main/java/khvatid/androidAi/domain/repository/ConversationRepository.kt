package khvatid.androidAi.domain.repository

import khvatid.androidAi.domain.models.AnswerModel
import khvatid.androidAi.domain.models.MessageModel
import kotlinx.coroutines.flow.Flow

interface ConversationRepository {

    suspend fun getAnswer(messageList: List<MessageModel>): Flow<AnswerModel>


}