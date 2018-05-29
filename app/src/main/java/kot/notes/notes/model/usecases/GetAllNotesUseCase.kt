package kot.notes.notes.model.usecases

import io.reactivex.Observable
import io.reactivex.Scheduler
import kot.notes.notes.data.entity.NoteEntity
import kot.notes.notes.data.repository.INotesRepository
import kot.notes.notes.model.usecases.UseCase
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by dzhafar on 23.05.18.
 */
class GetAllNotesUseCase : UseCase<List<NoteEntity>> {


    var repository: INotesRepository

    @Inject
    constructor(@Named("executor_thread") executorThread: Scheduler,
                @Named("ui_thread")  uiThread: Scheduler, repository: INotesRepository) : super(executorThread, uiThread) {
        this.repository = repository
    }

    override fun createObservableUseCase(): Observable<List<NoteEntity>> {
        return this.repository.getAllNotes()
    }

}