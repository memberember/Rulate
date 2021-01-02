package tl.rulate.ru.ui

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_book.*
import tl.rulate.ru.R
import tl.rulate.ru.ui.novels.NovelsViewModel

class BookFragment : Fragment() {
    private var novelsViewModel = NovelsViewModel
    private var bookViewModel = BookViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // слушатель на обновление данных книги
        bookViewModel.book.observe(viewLifecycleOwner, Observer {

            Picasso.with(context)
                .load(bookViewModel.book.value!!.img)
                .placeholder(R.drawable.ic_android_black_24dp)
                .error(R.drawable.ic_android_black_24dp)
                .into(iv_book_img)

            tv_book_description.setText(Html.fromHtml(bookViewModel.book.value!!.description))
            tv_book_title.setText(bookViewModel.book.value!!.t_title)



        })

        // запрашиваем данные по главе
        bookViewModel.book(novelsViewModel.lastBookId.value!!)

    }
}
