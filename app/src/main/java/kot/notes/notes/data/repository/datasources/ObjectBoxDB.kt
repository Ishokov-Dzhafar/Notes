package kot.notes.notes.data.repository.datasources

import io.objectbox.Box
import io.reactivex.Observable
import kot.notes.notes.MainApplication
import kot.notes.notes.data.entity.NoteEntity
import javax.inject.Inject

/**
 * Created by dzhafar on 26.05.18.
 */
class ObjectBoxDB: IDatabase {

    val noteBox: Box<NoteEntity> = MainApplication.getboxStore().boxFor(NoteEntity::class.java)

    @Inject
    constructor()

    override fun addNoteEntity(note: NoteEntity): Observable<Long> {
        return Observable.just(noteBox.put(note))
    }

    override fun deleteNoteEntity(note: NoteEntity): Observable<Unit> {
        return Observable.just(noteBox.remove(note))
    }

    override fun deleteNoteById(id_note: Long): Observable<Unit> {
        return Observable.just(noteBox.remove(id_note))
    }

    override fun getAllNotesEntity(): Observable<List<NoteEntity>> {
        return Observable.just(noteBox.all)
    }

    override fun getNoteEntityById(id_note: Long): Observable<NoteEntity> {
        return Observable.just(noteBox.get(id_note))
    }

}