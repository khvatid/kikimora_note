package khvatid.kikimora.domain.repository

import khvatid.kikimora.domain.models.AnswerModel
import khvatid.kikimora.domain.models.MessageModel
import kotlinx.coroutines.flow.Flow

interface ConversationRepository {

   suspend fun getAnswer(messageList: List<MessageModel>, token: String): Flow<AnswerModel>


}