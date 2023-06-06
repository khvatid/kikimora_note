package khvatid.androidAi.data.store.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class NetworkInstance {


    private var instance: OpenAiApi? = null

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
        //    addHeaderLenient(Headers.Builder(), "Authorization", TOKEN)
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(5, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun execute(): OpenAiApi {
        synchronized(this) {
            var instance = this.instance
            if (instance == null) {
                instance = retrofit.create(OpenAiApi::class.java)
                this.instance = instance
            }
            return this.instance!!
        }
    }

    companion object {
        private const val BASE_URL = "https://api.openai.com"
    }
}