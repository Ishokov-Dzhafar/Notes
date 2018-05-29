package kot.notes.notes.model.interactors.interfaces

import io.reactivex.observers.DisposableObserver
import kot.notes.notes.data.entity.NoteEntity

/**
 * Created by dzhafar on 26.05.18.
 */
interface AddNotesFragmentInteractor {
    fun addNote(disposable: DisposableObserver<Long>, note: NoteEntity)
}