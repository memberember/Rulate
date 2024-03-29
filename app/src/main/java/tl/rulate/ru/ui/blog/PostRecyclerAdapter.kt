import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_post.view.*
import tl.rulate.ru.JsonData.BlogJsonData
import tl.rulate.ru.R
import java.util.*

class PostRecyclerAdapter(val items: MutableList<BlogJsonData.PostData>) :
    RecyclerView.Adapter<PostRecyclerAdapter.RecHolder>() {
    var onItemClick: ((BlogJsonData.PostData) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecHolder {

        val inflater = LayoutInflater.from(parent!!.context)

        // указываем какую вьюху использовать, куда вставлять и заменять ли при вставке
        val view = inflater.inflate(
            R.layout.item_post,
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
        holder.itemView.tv_title.text = item.title
        holder.itemView.tv_post_content.text = Html.fromHtml(item.body)
        holder.itemView.tv_post_author.text = item.author
        holder.itemView.tv_post_date.text = "["+ Date(item.time.toLong()).toString()+"]"
    }

    // можно сделать пагинацию при помощи элементов, к примеру
    // когда тип элемента = 1 то значит что нужно выводить новую порцию данных
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    fun set(list: MutableList<BlogJsonData.PostData>) {
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

