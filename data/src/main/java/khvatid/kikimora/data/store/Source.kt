package khvatid.kikimora.data.store

import android.content.Context
import androidx.room.Room

class Source(context: Context) {

    val database: AppDatabase by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "kikimora-db")
            .fallbackToDestructiveMigration()
            .build()
    }




}