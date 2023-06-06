package khvatid.androidAi.data.source

import khvatid.androidAi.data.store.retrofit.NetworkInstance
import khvatid.androidAi.data.store.retrofit.OpenAiApi
import khvatid.androidAi.data.store.retrofit.models.GptResponseModel
import khvatid.androidAi.data.store.retrofit.models.RequestModel
import khvatid.androidAi.data.store.retrofit.models.ResultModel
import khvatid.androidAi.data.store.retrofit.source.OpenAiSource
import khvatid.androidAi.data.utils.OPEN_AI_TOKEN

class OpenAiSourceImp(network: NetworkInstance) : OpenAiSource {

    private val service: OpenAiApi = network.execute()

    override suspend fun getLanguageModelResponse(requestModel: RequestModel): ResultModel<GptResponseModel> {
        val response = service.postLMA(body = requestModel, token = TOKEN)
        return if (response.isSuccessful) {
            ResultModel.Success(response.body())
        } else {
            ResultModel.Failure(response.code(), response.message())
        }
    }


    companion object {
        private const val TOKEN = "Bearer $OPEN_AI_TOKEN"
    }
}