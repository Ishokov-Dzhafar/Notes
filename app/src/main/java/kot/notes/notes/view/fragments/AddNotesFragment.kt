package kot.notes.notes.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kot.notes.notes.MainApplication
import kot.notes.notes.R
import kot.notes.notes.data.entity.NoteEntity
import kot.notes.notes.presenter.AddNotesFragmentPresenterImpl
import kotlinx.android.synthetic.main.note_item.*
import org.reactivestreams.Subscription
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Created by dzhafar on 26.05.18.
 */

const val NOTES = "NOTES"

class AddNotesFragment: Fragment(), AddNotesFragmentPresenterImpl.View {

    private val TAG = "AddNotesFragment"

    @BindView(R.id.notesText)
    lateinit var notesET: EditText

    private var note = NoteEntity()

    @Inject
    lateinit var presenter: AddNotesFragmentPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        if (arguments != null && arguments!!.containsKey(NOTES)) {
            val bundle = arguments
            note = bundle!!.getSerializable(NOTES) as NoteEntity
        }
        retainInstance = true
        initializeDagger()
        presenter.view = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.add_notes_fragment, container, false)
        ButterKnife.bind(this, view)
        if (note.date != null) notesET.setText(note.text)
        return view
    }

    fun saveNote() {
        val date: Long = Calendar.getInstance().time.time
        if (note.date == null) {
            note = NoteEntity(date = date, text = notesET.text.toString())
        } else {
            note.date = date
            note.text = notesET.text.toString()
        }
        presenter.addNote(note)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.add_notes_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
         when (item!!.itemId) {
            R.id.done -> {
                closeFragment()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun closeFragment() {
        activity!!.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveNote()
    }


    private fun initializeDagger() {
        MainApplication.getmainComponent().inject(this)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {

    }

    override fun onAddNoteDone() {
        onDestroy()
    }

}