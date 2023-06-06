package khvatid.androidAi.data.store.retrofit.models

import com.google.gson.annotations.SerializedName

data class Choice(
    @SerializedName("finish_reason")
    val finishReason: String,
    @SerializedName("index")
    val index: Int,
    @SerializedName("message")
    val message: Message
)