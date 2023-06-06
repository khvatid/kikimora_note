package khvatid.androidAi.data.source

import khvatid.androidAi.data.store.retrofit.NetworkInstance
import khvatid.androidAi.data.store.retrofit.OpenAiApi
import khvatid.androidAi.data.store.retrofit.models.GptResponseModel
import khvatid.androidAi.data.store.retrofit.models.RequestModel
import khvatid.androidAi.data.store.retrofit.models.ResultModel
import khvatid.androidAi.data.store.retrofit.source.OpenAiSource


class OpenAiSourceImp(network: NetworkInstance) : OpenAiSource {

    private val service: OpenAiApi = network.execute()

    override suspend fun getLanguageModelResponse(requestModel: RequestModel,token : String): ResultModel<GptResponseModel> {
        val response = service.postLMA(body = requestModel, token = "Bearer $token")
        return if (response.isSuccessful) {
            ResultModel.Success(response.body())
        } else {
            ResultModel.Failure(response.code(), response.message())
        }
    }

}