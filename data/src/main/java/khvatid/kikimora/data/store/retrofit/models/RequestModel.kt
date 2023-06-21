package khvatid.kikimora.data.store.retrofit.models


import com.google.gson.annotations.SerializedName

data class RequestModel(
    @SerializedName("messages")
    val messages: List<Message> = emptyList(),
    @SerializedName("model")
    val model: String = "gpt-3.5-turbo"
)

