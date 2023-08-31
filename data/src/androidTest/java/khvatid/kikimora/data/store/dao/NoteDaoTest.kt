package khvatid.kikimora.data.store.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import khvatid.kikimora.data.store.AppDatabase
import khvatid.kikimora.data.store.entities.ContentEntity
import khvatid.kikimora.data.store.entities.NoteEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteDaoTest {
    private lateinit var noteDao: NoteDao
    private lateinit var db: AppDatabase


    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        noteDao = db.getNoteDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndGetNote() = runBlocking {
        val note = NoteEntity(id = 1, title = "Test Note")
        noteDao.upsertNote(note)
        val retrievedNote = noteDao.getNotes().first()
        Assert.assertEquals(retrievedNote.size, 1)
    }

    @Test
    fun insertAndGetNoteWithContent() = runBlocking {
        val note = NoteEntity(id = 1, title = "Test Note")
        noteDao.upsertNote(note)
        repeat(3){
            noteDao.upsertContent(
                ContentEntity(
                    id = 0,
                    noteId = 1,
                    type = "Audio",
                    content = "Test Test Test"
                )
            )
        }
        val expected = noteDao.getNoteWithContent(1).first()
        Assert.assertEquals(expected.content.size,3)
        Assert.assertEquals(expected.note.id, note.id)
    }
}