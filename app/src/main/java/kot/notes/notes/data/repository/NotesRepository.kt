package kot.notes.notes.data.repository

import io.reactivex.Observable
import kot.notes.notes.data.entity.NoteEntity
import kot.notes.notes.data.repository.datasources.IDatabase
import kot.notes.notes.data.repository.datasources.ObjectBoxDB
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
    }

}