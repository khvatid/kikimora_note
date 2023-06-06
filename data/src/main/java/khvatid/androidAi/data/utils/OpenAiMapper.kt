package khvatid.androidAi.data.utils

import khvatid.androidAi.data.store.retrofit.models.GptResponseModel
import khvatid.androidAi.data.store.retrofit.models.Message
import khvatid.androidAi.data.store.retrofit.models.RequestModel
import khvatid.androidAi.domain.models.MessageModel

class OpenAiMapper {

    fun map(messageList: List<MessageModel>): RequestModel {
        return RequestModel(messages = messageList.map { it.toMessage() })
    }

    fun map(response: GptResponseModel): MessageModel {
        return response.choices[0].message.toMessageModel()
    }

    private fun Message.toMessageModel(): MessageModel {
        return MessageModel(content = this.content, role = this.role)
    }

    private fun MessageModel.toMessage(): Message {
        return Message(content = this.content, role = this.role)
    }
}