package kot.notes.notes.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kot.notes.notes.data.repository.INotesRepository
import kot.notes.notes.data.repository.NotesRepository
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by dzhafar on 22.05.18.
 */
@Module
class MainModule {

    @Provides
    @Singleton
    fun provideNoteRepository(notesRepository: NotesRepository): INotesRepository {
        return notesRepository
    }

    @Provides
    @Named("executor_thread")
    internal fun provideRepositoryThread(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @Named("ui_thread")
    internal fun provideUiThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}