package khvatid.kikimora.features.settings

import android.os.Build
import androidx.annotation.RequiresApi

interface SettingsScreenContract{

    data class State(
        val isDynamicTheme: Boolean = false,
        val token : String = "",
    )

    sealed class Events{
        @RequiresApi(Build.VERSION_CODES.S)
        object UseDynamicTheme: Events()
        data class OnTokenChanged(val value: String): Events()
        object AcceptTokenChanged: Events()

    }
}