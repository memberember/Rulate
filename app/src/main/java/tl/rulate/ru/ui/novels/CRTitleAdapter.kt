import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_card_novel.view.*
import tl.rulate.ru.R

class CRTitleAdapter(val items: MutableList<Title>) :
    RecyclerView.Adapter<CRTitleAdapter.RecHolder>() {
    var onItemClick: ((Title) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecHolder {

        val inflater = LayoutInflater.from(parent!!.context)

        // указываем какую вьюху использовать, куда вставлять и заменять ли при вставке
        val view = inflater.inflate(
            R.layout.item_card_novel,
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

        holder.itemView.iv_title
        holder.itemView.tv_title.setText(item.t_title)

        Picasso.with(holder.itemView.iv_title.context)
            .load(if (item.img.isNotBlank()) Uri.parse( item.img ); else
                Uri.parse("android.resource://tl.rulate.ru/drawable/default_image"))
            .error(R.drawable.default_image)
            .into(holder.itemView.iv_title)
    }

    // можно сделать пагинацию при помощи элементов, к примеру
    // когда тип элемента = 1 то значит что нужно выводить новую порцию данных
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    fun set(list: MutableList<Title>) {
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

