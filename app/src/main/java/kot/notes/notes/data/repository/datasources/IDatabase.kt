package kot.notes.notes.data.repository.datasources

import io.reactivex.Observable
import kot.notes.notes.data.entity.NoteEntity

/**
 * Created by dzhafar on 26.05.18.
 */
interface IDatabase {

    fun addNoteEntity(note: NoteEntity): Observable<Long>

    fun deleteNoteEntity(note: NoteEntity): Observable<Unit>

    fun deleteNoteById(id_note: Long): Observable<Unit>

    fun getAllNotesEntity(): Observable<List<NoteEntity>>

    fun getNoteEntityById(id_note: Long): Observable<NoteEntity>

}