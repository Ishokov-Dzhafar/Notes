package kot.notes.notes.data.repository

import io.reactivex.Observable
import io.reactivex.functions.Function
import kot.notes.notes.data.entity.NoteEntity
import kot.notes.notes.data.entity.SortNoteByDate
import kot.notes.notes.data.repository.datasources.IDatabase
import kot.notes.notes.data.repository.datasources.ObjectBoxDB
import java.util.*
import javax.inject.Inject

/**
 * Created by dzhafar on 22.05.18.
 */
class NotesRepository: INotesRepository {

    var objectBoxDB: IDatabase

    @Inject
    constructor(objectBoxDB: ObjectBoxDB) {
        this.objectBoxDB = objectBoxDB
    }

    override fun addNote(note: NoteEntity): Observable<Long> {
        return objectBoxDB.addNoteEntity(note)
    }

    override fun deleteNote(note: NoteEntity): Observable<Unit> {
        return objectBoxDB.deleteNoteEntity(note)
    }

    override fun getNotesById(noteId: Long): Observable<NoteEntity> {
        return objectBoxDB.getNoteEntityById(noteId)
    }

    override fun getAllNotes(): Observable<List<NoteEntity>> {

        return objectBoxDB.getAllNotesEntity()
                .map(object : Function<List<NoteEntity>, List<NoteEntity>> {
                    override fun apply(t: List<NoteEntity>): List<NoteEntity> {
                        val sortByDate = SortNoteByDate()
                        Collections.sort(t, sortByDate)
                        return t
                    }
                })

    }

    override fun deleteNotes(noteIds: List<NoteEntity>): Observable<Unit> {
        return objectBoxDB.deleteNotes(noteIds)
    }
}