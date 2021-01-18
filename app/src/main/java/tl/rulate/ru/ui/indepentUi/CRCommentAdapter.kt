package tl.rulate.ru.ui.indepentUi

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_comment.view.*
import tl.rulate.ru.JsonData.BookJsonData
import tl.rulate.ru.R
import java.util.*

class CRCommentAdapter(val items: MutableList<BookJsonData.Comment>) :
    RecyclerView.Adapter<CRCommentAdapter.RecHolder>() {
    var onItemClick: ((BookJsonData.Comment) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecHolder {

        val inflater = LayoutInflater.from(parent!!.context)

        // указываем какую вьюху использовать, куда вставлять и заменять ли при вставке
        val view = inflater.inflate(
            R.layout.item_comment,
            parent,
            false
        )
        return RecHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecHolder, position: Int) {
        val defaultImage = "https://s.4pda.to/EmSL2n4fy6AgWnDxCWgsWIP5oDmaNp0Uxhs8.jpg"

        val item = items[position]!!

        holder.itemView.tv_author_name.text = item.author
        holder.itemView.tv_comment_date.text="["+Date(item.time.toLong()).toString()+"]"
        holder.itemView.tv_comment_body.text = item.body

        Picasso.with(holder.itemView.iv_comment_avatar.context)
            .load(if (item.avatar.isNotBlank()) Uri.parse( item.avatar ); else
                Uri.parse("android.resource://tl.rulate.ru/drawable/default_image"))
            .error(R.drawable.default_image)
            .into(holder.itemView.iv_comment_avatar)

    }

    // можно сделать пагинацию при помощи элементов, к примеру
    // когда тип элемента = 1 то значит что нужно выводить новую порцию данных
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    fun set(list: MutableList<BookJsonData.Comment>) {
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

