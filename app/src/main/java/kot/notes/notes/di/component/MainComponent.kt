package kot.notes.notes.di.component

import dagger.Component
import kot.notes.notes.di.module.MainModule
import kot.notes.notes.view.fragments.AddNotesFragment
import kot.notes.notes.view.fragments.MainFragment
import javax.inject.Singleton

/**
 * Created by dzhafar on 22.05.18.
 */
@Singleton
@Component(modules = arrayOf(MainModule::class))
interface MainComponent {

    fun inject(mainFragment: MainFragment)
    fun inject(addNotesFragment: AddNotesFragment)

}