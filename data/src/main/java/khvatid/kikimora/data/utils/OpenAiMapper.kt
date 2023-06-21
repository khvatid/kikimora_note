package khvatid.kikimora.data.utils

import khvatid.kikimora.data.store.retrofit.models.GptResponseModel
import khvatid.kikimora.data.store.retrofit.models.Message
import khvatid.kikimora.data.store.retrofit.models.RequestModel
import khvatid.kikimora.domain.models.MessageModel

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