import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_wide_card_novel.view.*
import tl.rulate.ru.JsonData.GetReadyJsonData
import tl.rulate.ru.R

class WideCardRecyclerAdapter(val items: MutableList<GetReadyJsonData.Title>) :
    RecyclerView.Adapter<WideCardRecyclerAdapter.RecHolder>() {
    var onItemClick: ((GetReadyJsonData.Title) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecHolder {

        val inflater = LayoutInflater.from(parent!!.context)

        // указываем какую вьюху использовать, куда вставлять и заменять ли при вставке
        val view = inflater.inflate(
            R.layout.item_wide_card_novel,
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

        holder.itemView.tv_title.setText(item.t_title)
        holder.itemView.tv_chapter.setText(item.title)
        holder.itemView.tv_lang.setText(item.lang)

        Picasso.with(holder.itemView.title_img2.context)
            .load(if (item.img.isNotBlank()) item.img else defaultImage)
            .into(holder.itemView.title_img2)
    }

    // можно сделать пагинацию при помощи элементов, к примеру
    // когда тип элемента = 1 то значит что нужно выводить новую порцию данных
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    fun set(list: MutableList<GetReadyJsonData.Title>) {
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

