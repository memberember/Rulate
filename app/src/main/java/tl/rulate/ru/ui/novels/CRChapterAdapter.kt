import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_chapter.view.*
import tl.rulate.ru.JsonData.BookJsonData
import tl.rulate.ru.R

class CRChapterAdapter(val items: MutableList<BookJsonData.Chapter>) :
    RecyclerView.Adapter<CRChapterAdapter.RecHolder>() {
    var onItemClick: ((BookJsonData.Chapter) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecHolder {

        val inflater = LayoutInflater.from(parent!!.context)

        // указываем какую вьюху использовать, куда вставлять и заменять ли при вставке
        val view = inflater.inflate(
            R.layout.item_chapter,
            parent,
            false
        )
        return RecHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecHolder, position: Int) {

        val item = items[position]!!
        holder.itemView.tv_chapter_title.setText(item.title)
        holder.itemView.tv_chapter_status.setText(item.status)
        holder.itemView.tv_chapter_can_read.setText(
            if (item.can_read) "Готово" else "Переводится"
        )
    }

    // можно сделать пагинацию при помощи элементов, к примеру
    // когда тип элемента = 1 то значит что нужно выводить новую порцию данных
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    fun set(list: MutableList<BookJsonData.Chapter>) {
        this.items.clear()
        this.items.addAll(list)
        this.notifyDataSetChanged()
    }

    inner class RecHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition])
            }
        }

    }
}

