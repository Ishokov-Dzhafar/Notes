package kot.notes.notes.model.interactors

import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import kot.notes.notes.data.entity.NoteEntity
import kot.notes.notes.model.interactors.interfaces.MainFragmentInteractor
import kot.notes.notes.model.usecases.AddNoteUseCase
import kot.notes.notes.model.usecases.GetAllNotesUseCase
import javax.inject.Inject

/**
 * Created by dzhafar on 25.05.18.
 */
class MainFragmentInteractorImpl: MainFragmentInteractor {

    val addNoteUseCase: AddNoteUseCase
    val getAllNotesUseCase: GetAllNotesUseCase

    @Inject
    constructor(addNoteUseCase: AddNoteUseCase, getAllNotesUseCase: GetAllNotesUseCase) {
        this.addNoteUseCase = addNoteUseCase
        this.getAllNotesUseCase = getAllNotesUseCase
    }


    override fun addNote(disposable: DisposableObserver<Long>, note: NoteEntity) {
        addNoteUseCase.note = note
        addNoteUseCase.execute(disposable)
    }

    override fun getAllNotes(disposable: DisposableObserver<List<NoteEntity>>) {
        getAllNotesUseCase.execute(disposable)
    }

}