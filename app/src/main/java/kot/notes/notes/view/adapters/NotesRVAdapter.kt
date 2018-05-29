package kot.notes.notes.view.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.OnClick
import kot.notes.notes.R
import kot.notes.notes.data.entity.NoteEntity
import kotlinx.android.synthetic.main.note_item.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by dzhafar on 26.05.18.
 */
class NotesRVAdapter: RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    val TAG = "NotesRVAdapter"

    private var mItems: MutableList<NoteEntity> = mutableListOf()
    private lateinit var clickListener: OnClickItem

    fun setList(list: List<NoteEntity>) {
        mItems.clear()
        mItems.addAll(list)
    }

    fun setListener(click: OnClickItem) {
        clickListener = click
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mItems.get(position)
        holder.text.text = item.text
        val format = SimpleDateFormat("dd MMM HH:mm:ss yyyy")
        val dateF = Date(item.date!!)
        holder.date.text = format.format(dateF)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.note_item, parent, false)
        return ViewHolder(view).listen { position, type ->
            val item = mItems.get(position)
            clickListener.onClick(item)
            Log.d(TAG, "click -> " + position)
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text = itemView.text!!
        var date = itemView.date
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }

    interface OnClickItem {
        fun onClick(noteEntity: NoteEntity)
    }
}