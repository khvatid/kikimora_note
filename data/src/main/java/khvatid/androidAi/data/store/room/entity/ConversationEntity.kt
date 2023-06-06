package khvatid.androidAi.data.store.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ConversationEntity(

    @PrimaryKey(autoGenerate = true)
    val id : String,
    @SerializedName("name")
    val name: String?
)