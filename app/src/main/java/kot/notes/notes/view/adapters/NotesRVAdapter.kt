package kot.notes.notes.view.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import butterknife.OnClick
import kot.notes.notes.R
import kot.notes.notes.data.entity.NoteEntity
import kot.notes.notes.presenter.MainFragmentPresenterImpl
import kotlinx.android.synthetic.main.note_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import android.opengl.ETC1.getHeight
import android.view.animation.TranslateAnimation



/**
 * Created by dzhafar on 26.05.18.
 */
class NotesRVAdapter: RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    val TAG = "NotesRVAdapter"

    private var mItems: MutableList<NoteEntity> = mutableListOf()
    private lateinit var clickListener: OnClickItem
    private lateinit var view: MainFragmentPresenterImpl.View
    private var selectedIds: MutableList<Int> = mutableListOf()
    private var isSelected = false

    fun setList(list: List<NoteEntity>) {
        mItems.clear()
        mItems.addAll(list)
        notifyDataSetChanged()
    }

    fun setListener(click: OnClickItem) {
        clickListener = click
    }

    fun setView(view: MainFragmentPresenterImpl.View) {
        this.view = view
    }

    fun getSelectedIds(): List<NoteEntity> {
        val selectedItems: MutableList<NoteEntity> = mutableListOf()
        selectedIds.toList().mapTo(selectedItems) { mItems[it] }
        return selectedItems
    }

    fun deletedIds() {
        selectedIds.clear()
        isSelected = false
        view.hideSelectedLayout()
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mItems.get(position)
        holder.text.text = item.text
        val format = SimpleDateFormat("dd MMM HH:mm:ss yyyy", Locale.getDefault())
        val dateF = Date(item.date!!)
        holder.date.text = format.format(dateF)
        if (isSelected) {
            holder.checkLayout.visibility = View.VISIBLE
            if (selectedIds.contains(position)) {
                holder.checkFlag.visibility = View.VISIBLE
            } else {
                holder.checkFlag.visibility = View.GONE
            }
        } else {
            holder.checkLayout.visibility = View.GONE
        }
    }


    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.note_item, parent, false)
        return ViewHolder(view)
                .listen { position, type ->
                    val item = mItems.get(position)
                    if (selectedIds.size > 0) {
                        addOrRemoveSelectedId(position)
                    } else {
                        clickListener.onClick(item)
                    }
                    Log.d(TAG, "click -> " + position)
                }
                .listenLongClick { position, type ->
                    val item = mItems.get(position)
                    clickListener.onLongClick(item)
                    addOrRemoveSelectedId(position)
                    Log.d(TAG, "long click -> " + position)
                    return@listenLongClick true
                }
    }

    fun addOrRemoveSelectedId(position: Int) {
        if (selectedIds.contains(position)) {
            selectedIds.remove(position)
            if (selectedIds.size == 0 && isSelected) {
                view.hideSelectedLayout()
                isSelected = false
            }
        } else {
            if (!isSelected)  {
                view.showSelectedLayout()
                isSelected = true
            }
            selectedIds.add(position)
        }
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text = itemView.text!!
        var date = itemView.date
        var checkFlag = itemView.checkFlag
        var checkLayout = itemView.checkLayout
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }

    fun <T : RecyclerView.ViewHolder> T.listenLongClick(event: (position: Int, type: Int) -> Boolean): T {
        itemView.setOnLongClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }

    interface OnClickItem {
        fun onClick(noteEntity: NoteEntity)
        fun onLongClick(noteEntity: NoteEntity)
    }
}