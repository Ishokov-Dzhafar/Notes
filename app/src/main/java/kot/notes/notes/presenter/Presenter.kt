package kot.notes.notes.presenter

/**
 * Created by dzhafar on 23.05.18.
 */
open class Presenter<T : Presenter.View> {

    var view: T? = null

    fun initialize() {

    }

    interface View {

        fun showLoading()

        fun hideLoading()
    }
}