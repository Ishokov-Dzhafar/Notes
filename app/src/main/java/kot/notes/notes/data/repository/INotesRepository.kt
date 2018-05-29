package kot.notes.notes.data.repository

import io.reactivex.Observable
import kot.notes.notes.data.entity.NoteEntity

/**
 * Created by dzhafar on 22.05.18.
 */
interface INotesRepository {

    fun getAllNotes(): Observable<List<NoteEntity>>

    fun getNotesById(noteId: Long): Observable<NoteEntity>

    fun addNote(note: NoteEntity): Observable<Long>

    fun deleteNote(note: NoteEntity): Observable<Unit>
}