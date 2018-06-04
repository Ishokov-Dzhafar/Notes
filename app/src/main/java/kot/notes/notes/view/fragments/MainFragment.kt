package kot.notes.notes.view.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import android.opengl.Visibility
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.animation.TranslateAnimation
import android.widget.*
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
    @BindView(R.id.selectedLayout)
    lateinit var selectedLayout: RelativeLayout
    @BindView(R.id.remove)
    lateinit var removeBtn: ImageButton
    @BindView(R.id.cancel)
    lateinit var cancelBtn: ImageButton

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
        val llm = GridLayoutManager(context, 2, VERTICAL, false)
        notesRV.layoutManager = llm
        mAdapter = NotesRVAdapter()
        mAdapter.setListener(this)
        mAdapter.setView(this)
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

    override fun onLongClick(noteEntity: NoteEntity) {

    }

    override fun onSuccessNotes(list: List<NoteEntity>) {
        mAdapter.setList(list)
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

    override fun showSelectedLayout() {
        slideUp(selectedLayout)
    }

    override fun hideSelectedLayout() {
        slideDown(selectedLayout)
    }

    @OnClick(R.id.remove)
    fun deleteNotes() {
        val ids = mAdapter.getSelectedIds()
        presenter.deletedNotes(ids)
        mAdapter.deletedIds()
    }

    @OnClick(R.id.cancel)
    fun clearSelectedIds() {
        mAdapter.deletedIds()
    }

    // slide the view from below itself to the current position
    fun slideUp(view: View) {
        selectedLayout.visibility = View.VISIBLE
        val animate = TranslateAnimation(
                0f, // fromXDelta
                0f, // toXDelta
                view.height.toFloat(), // fromYDelta
                0f)                // toYDelta
        animate.duration = 500
        animate.fillAfter = true
        view.startAnimation(animate)
    }

    // slide the view from its current position to below itself
    fun slideDown(view: View) {
        val animate = TranslateAnimation(
                0f, // fromXDelta
                0f, // toXDelta
                0f, // fromYDelta
                view.height.toFloat()) // toYDelta
        animate.duration = 500
        animate.fillAfter = true
        view.startAnimation(animate)
        selectedLayout.visibility = View.GONE
    }
}