package kot.notes.notes.presenter

import android.util.Log
import io.reactivex.observers.DisposableObserver
import kot.notes.notes.data.entity.NoteEntity
import kot.notes.notes.model.interactors.AddNotesFragmentInteractorImpl
import kot.notes.notes.model.interactors.interfaces.AddNotesFragmentInteractor
import javax.inject.Inject

/**
 * Created by dzhafar on 26.05.18.
 */
class AddNotesFragmentPresenterImpl: Presenter<AddNotesFragmentPresenterImpl.View> {

    private val TAG = "AddNotesFragPresenter"

    private val interactor: AddNotesFragmentInteractor

    @Inject
    constructor(interactor: AddNotesFragmentInteractorImpl) {
        this.interactor = interactor
    }

    fun addNote(note: NoteEntity) {
        interactor.addNote(object: DisposableObserver<Long>() {
            override fun onError(e: Throwable) {
            }

            override fun onComplete() {

            }

            override fun onNext(t: Long) {
                Log.d(TAG, "new notes ID: " + t)
            }

        }, note)
    }



    public interface View: Presenter.View {
        fun onAddNoteDone()
    }
}