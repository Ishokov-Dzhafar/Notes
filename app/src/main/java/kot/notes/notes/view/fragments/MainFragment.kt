package kot.notes.notes.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import kot.notes.notes.MainApplication
import kot.notes.notes.R
import kot.notes.notes.data.entity.NoteEntity
import kot.notes.notes.presenter.MainFragmentPresenterImpl
import java.util.*
import javax.inject.Inject
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout.VERTICAL
import kot.notes.notes.view.adapters.NotesRVAdapter


/**
 * Created by dzhafar on 22.05.18.
 */
class MainFragment : Fragment(), MainFragmentPresenterImpl.View, NotesRVAdapter.OnClickItem {


    val TAG : String = "MainFragment"

    @BindView(R.id.fab)
    lateinit var fab: FloatingActionButton
    @BindView(R.id.notesRV)
    lateinit var notesRV: RecyclerView

    lateinit var mAdapter: NotesRVAdapter

    @Inject
    lateinit var presenter: MainFragmentPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        initializeDagger()
        presenter.view = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.main_fragment, container, false)
        ButterKnife.bind(this, view)
        val llm: GridLayoutManager = GridLayoutManager(context, 2, VERTICAL, false)
        notesRV.layoutManager = llm
        mAdapter = NotesRVAdapter()
        mAdapter.setListener(this)
        notesRV.adapter = mAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getAllNotes()
    }

    @OnClick(R.id.fab)
    fun onClickFab() {
        val ft: FragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        val fragment: Fragment = AddNotesFragment()
        ft.replace(R.id.container, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun onClick(noteEntity: NoteEntity) {
        val bundle = Bundle()
        bundle.putSerializable(NOTES, noteEntity)
        val ft: FragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        val fragment: Fragment = AddNotesFragment()
        fragment.arguments = bundle
        ft.replace(R.id.container, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun onSuccessNotes(list: List<NoteEntity>) {
        mAdapter.setList(list)
        notesRV.adapter.notifyDataSetChanged()
    }

    private fun initializeDagger() {
        MainApplication.getmainComponent().inject(this)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {
    }

    override fun showToast(string: String) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }
}