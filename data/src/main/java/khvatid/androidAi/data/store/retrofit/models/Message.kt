package khvatid.androidAi.data.store.retrofit.models

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("content")
    val content: String,
    @SerializedName("role")
    val role: String
)