package kot.notes.notes.presenter

import android.graphics.Bitmap
import android.util.Log
import io.reactivex.observers.DisposableObserver
import kot.notes.notes.data.entity.NoteEntity
import kot.notes.notes.model.interactors.MainFragmentInteractorImpl
import kot.notes.notes.model.interactors.interfaces.MainFragmentInteractor
import javax.inject.Inject

/**
 * Created by dzhafar on 24.05.18.
 */
class MainFragmentPresenterImpl: Presenter<MainFragmentPresenterImpl.View> {

    private var interactor: MainFragmentInteractor

    @Inject
    constructor(interactor: MainFragmentInteractorImpl) {
        this.interactor = interactor
    }

    fun addNote(note: NoteEntity) {
        interactor.addNote(object: DisposableObserver<Long>() {
            override fun onNext(t: Long) {
                view!!.showToast(t.toString())
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

            override fun onComplete() {

            }
        }, note)
    }

    fun getAllNotes() {
        interactor.getAllNotes(object: DisposableObserver<List<NoteEntity>>() {
            override fun onNext(t: List<NoteEntity>) {
                view!!.onSuccessNotes(t)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

            override fun onComplete() {

            }
        })
    }


     interface View: Presenter.View {
        fun showToast(string: String)
        fun onSuccessNotes(list: List<NoteEntity>)
    }
}