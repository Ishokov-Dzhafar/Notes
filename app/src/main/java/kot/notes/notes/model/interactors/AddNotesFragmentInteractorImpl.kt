package kot.notes.notes.model.interactors

import io.reactivex.observers.DisposableObserver
import kot.notes.notes.data.entity.NoteEntity
import kot.notes.notes.model.interactors.interfaces.AddNotesFragmentInteractor
import kot.notes.notes.model.usecases.AddNoteUseCase
import javax.inject.Inject

/**
 * Created by dzhafar on 26.05.18.
 */
class AddNotesFragmentInteractorImpl: AddNotesFragmentInteractor {

    private val addNoteUseCase: AddNoteUseCase

    @Inject
    constructor(addNoteUseCase: AddNoteUseCase) {
        this.addNoteUseCase = addNoteUseCase
    }


    override fun addNote(disposable: DisposableObserver<Long>, note: NoteEntity) {
        addNoteUseCase.note = note
        addNoteUseCase.execute(disposable)
    }

}