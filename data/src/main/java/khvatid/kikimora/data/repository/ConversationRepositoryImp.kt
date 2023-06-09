package khvatid.kikimora.data.repository

import android.util.Log
import khvatid.kikimora.data.store.retrofit.models.GptResponseModel
import khvatid.kikimora.data.store.retrofit.models.Message
import khvatid.kikimora.data.store.retrofit.models.RequestModel
import khvatid.kikimora.data.store.retrofit.models.ResultModel
import khvatid.kikimora.data.store.retrofit.source.OpenAiSource
import khvatid.kikimora.domain.models.AnswerModel
import khvatid.kikimora.domain.models.MessageModel
import khvatid.kikimora.domain.repository.ConversationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ConversationRepositoryImp(
   private val openAiSource: OpenAiSource,
) : ConversationRepository {

   override suspend fun getAnswer(
      messageList: List<MessageModel>,
      token: String
   ): Flow<AnswerModel> =
      callbackFlow {
         Log.i("REPO", "${messageList.last()}")
         trySend(AnswerModel.Loading)
         try {
            val result =
               openAiSource.getLanguageModelResponse(
                  requestModel = RequestModel(messageList.map { it.toMessage() }),
                  token = token
               )
            when (result) {
               is ResultModel.Failure -> {
                  trySend(AnswerModel.Error(result.message + result.code))
               }

               is ResultModel.Success -> {
                  trySend(
                     AnswerModel.Success(
                        result.data?.toMessageModel() ?: throw NullPointerException()
                     )
                  )
               }
            }
         } catch (e: Exception) {
            trySend(AnswerModel.Error("${e.message}"))
         }
         close()
      }


   private fun GptResponseModel.toMessageModel(): MessageModel {
      val message = this.choices[0].message
      return MessageModel(content = message.content, role = message.role)
   }

   private fun MessageModel.toMessage(): Message {
      return Message(content = content, role = role)
   }
}