package kot.notes.notes.model.interactors.interfaces

import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import kot.notes.notes.data.entity.NoteEntity

/**
 * Created by dzhafar on 25.05.18.
 */
interface MainFragmentInteractor {

    fun addNote(disposable: DisposableObserver<Long>, note: NoteEntity)

    fun getAllNotes(disposable: DisposableObserver<List<NoteEntity>>)

    fun deletedNotes(disposable: DisposableObserver<Unit>, list: List<NoteEntity>)
}