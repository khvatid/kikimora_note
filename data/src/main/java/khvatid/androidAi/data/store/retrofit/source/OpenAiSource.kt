package khvatid.androidAi.data.store.retrofit.source

import khvatid.androidAi.data.store.retrofit.models.GptResponseModel
import khvatid.androidAi.data.store.retrofit.models.RequestModel
import khvatid.androidAi.data.store.retrofit.models.ResultModel

interface OpenAiSource {
    suspend fun getLanguageModelResponse(requestModel: RequestModel, token: String): ResultModel<GptResponseModel>
}