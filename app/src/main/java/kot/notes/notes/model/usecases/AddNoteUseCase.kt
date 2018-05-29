package kot.notes.notes.model.usecases

import io.reactivex.Observable
import io.reactivex.Scheduler
import kot.notes.notes.data.entity.NoteEntity
import kot.notes.notes.data.repository.INotesRepository
import kot.notes.notes.data.repository.NotesRepository
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by dzhafar on 23.05.18.
 */
class AddNoteUseCase : UseCase<Long> {

    var note: NoteEntity
    private var repository: INotesRepository

    @Inject
    constructor(@Named("executor_thread") executorThread: Scheduler,
                @Named("ui_thread") uiThread: Scheduler, repository: NotesRepository, note: NoteEntity) : super(executorThread, uiThread) {
        this.repository = repository
        this.note = note
    }

    override fun createObservableUseCase(): Observable<Long> {
        return this.repository.addNote(note)
    }

}