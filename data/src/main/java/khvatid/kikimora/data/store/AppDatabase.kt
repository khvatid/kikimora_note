package khvatid.kikimora.data.store

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    version = 1,
    entities = [
    ],
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

}