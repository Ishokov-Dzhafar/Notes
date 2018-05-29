package kot.notes.notes

import android.app.Application
import android.content.Context
import io.objectbox.BoxStore
import kot.notes.notes.data.entity.MyObjectBox
import kot.notes.notes.di.component.DaggerMainComponent
import kot.notes.notes.di.component.MainComponent
import kot.notes.notes.di.module.MainModule

/**
 * Created by dzhafar on 22.05.18.
 */
class MainApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MainApplication? = null
        private lateinit var mainComponent : MainComponent
        private lateinit var boxStore: BoxStore

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

        fun getmainComponent() : MainComponent {
            return mainComponent
        }

        fun getboxStore() : BoxStore {
            return boxStore
        }

    }

    override fun onCreate() {
        super.onCreate()
        createMainComponent()
        boxStore = MyObjectBox.builder().androidContext(applicationContext).build()
    }

    fun createMainComponent() {
        mainComponent = DaggerMainComponent.builder().mainModule(MainModule()).build()
    }
}