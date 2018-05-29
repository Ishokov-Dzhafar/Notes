package kot.notes.notes.model.usecases

import io.reactivex.Observable
import io.reactivex.Scheduler
import kot.notes.notes.data.entity.NoteEntity
import kot.notes.notes.data.repository.INotesRepository
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by dzhafar on 23.05.18.
 */
class GetNotesByIdUseCase : UseCase<NoteEntity> {

    var id: Long
    var repository: INotesRepository

    @Inject
    constructor(@Named("executor_thread") executorThread: Scheduler,
                @Named("ui_thread") uiThread: Scheduler, repository: INotesRepository, id: Long) : super(executorThread, uiThread) {
        this.repository = repository
        this.id = id
    }

    override fun createObservableUseCase(): Observable<NoteEntity> {
        return this.repository.getNotesById(id)
    }

}