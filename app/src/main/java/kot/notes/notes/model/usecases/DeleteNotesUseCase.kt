package kot.notes.notes.model.usecases

import io.reactivex.Observable
import io.reactivex.Scheduler
import kot.notes.notes.data.entity.NoteEntity
import kot.notes.notes.data.repository.INotesRepository
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by dzhafar on 04.06.18.
 */
class DeleteNotesUseCase : UseCase<Unit> {

    lateinit var notes: List<NoteEntity>
    private var repository: INotesRepository

    @Inject
    constructor(@Named("executor_thread") executorThread: Scheduler,
                @Named("ui_thread") uiThread: Scheduler, repository: INotesRepository) : super(executorThread, uiThread) {
        this.repository = repository
    }

    fun setnotes(notes: List<NoteEntity>) {
        this.notes = notes
    }

    override fun createObservableUseCase(): Observable<Unit> {
        return this.repository.deleteNotes(notes)
    }

}