package khvatid.androidAi.data.store.retrofit


import khvatid.androidAi.data.store.retrofit.models.RequestModel
import khvatid.androidAi.data.store.retrofit.models.GptResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface OpenAiApi {

    @POST("/v1/chat/completions")
    suspend fun postLMA(@Body body: RequestModel, @Header("Authorization") token : String): Response<GptResponseModel>


}