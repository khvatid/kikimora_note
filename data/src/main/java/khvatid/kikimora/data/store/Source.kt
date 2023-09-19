package khvatid.kikimora.data.store

import android.content.Context
import androidx.room.Room
import khvatid.kikimora.data.store.source.NotesSource

class Source(context: Context) {

    val database: AppDatabase by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "kikimora-db")
            .fallbackToDestructiveMigration()
            .build()
    }

    val notesSource : NotesSource by lazy {
        NotesSource(database.getNoteDao())
    }


}