package khvatid.kikimora.domain.repository

import kotlinx.coroutines.flow.Flow

interface AppSettingRepository {

    fun getIsDynamicTheme(): Flow<Boolean>
    suspend fun setIsDynamicTheme(value : Boolean)

}