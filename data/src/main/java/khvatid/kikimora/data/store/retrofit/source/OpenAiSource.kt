package khvatid.kikimora.data.store.retrofit.source

import khvatid.kikimora.data.store.retrofit.models.GptResponseModel
import khvatid.kikimora.data.store.retrofit.models.RequestModel
import khvatid.kikimora.data.store.retrofit.models.ResultModel

interface OpenAiSource {
    suspend fun getLanguageModelResponse(requestModel: RequestModel, token: String): ResultModel<GptResponseModel>
}