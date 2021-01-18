package tl.rulate.ru.ui.indepentUi

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_book.*
import tl.rulate.ru.Constants
import tl.rulate.ru.R
import tl.rulate.ru.viewModels.MainViewModel

class BookFragment : Fragment() {
    private var bookViewModel = BookViewModel
    private var mainViewModel = MainViewModel

    var chaptersAdapter = CRChapterAdapter(mutableListOf())
    var commentsAdapter = CRCommentAdapter(mutableListOf())


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

        prepareView()

        setAdapters()

        setObservers()

        setClickListeners()
    }

    // подготовительные операции
    private fun prepareView() {
        // todo сделать сохранение и удаление в кеше закладок
        if (mainViewModel.isUserReadBook() || (mainViewModel.lastBookId.value != -1)){
            tv_book_warning.visibility = View.GONE

            // запрашиваем данные по книге
            bookViewModel.book(mainViewModel.lastBookId.value?:mainViewModel.sharedPref.book.lastBookId)
        }

        if (!mainViewModel.isUserAuthorized()){
            btn_add_to_bookmark.visibility = View.GONE
        }
    }

    // установка обработчиков
    private fun setClickListeners() {

        // слушатель на нажатие главы
        chaptersAdapter.onItemClick = { chapter ->
            Log.d(Constants.DEBUG,chapter.id.toString())
            mainViewModel.lastChapterId.value = chapter.id
        }


        // todo сделать возможность убирать из закладок
        btn_add_to_bookmark.setOnClickListener {
            bookViewModel.addToBookmark(
                bookViewModel.book.value!!.id,
                mainViewModel.sharedPref.user.token
            )
        }
    }

    // установка обзерверов
    private fun setObservers() {
//        bookViewModel.toastMessage.observe(viewLifecycleOwner, Observer {
//            Toast.makeText(nav_host_fragment.context, it, it.length).show()
//        })

        // слушатель на обновление данных книги
        bookViewModel.book.observe(viewLifecycleOwner, Observer {
            it.let {
                Picasso.with(context)
                    .load(it.img)
                    .placeholder(R.drawable.ic_android_black_24dp)
                    .error(R.drawable.ic_android_black_24dp)
                    .into(iv_book_img)
                tv_book_description.setText(Html.fromHtml(bookViewModel.book.value!!.description))
                tv_book_title.setText(bookViewModel.book.value!!.t_title)
                chaptersAdapter.set(it.chapters.toMutableList())
            }
        })
    }

    // установка адаптеров для списка глав и комментов
    private fun setAdapters() {

        rv_chapters.adapter = chaptersAdapter
        rv_chapters.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        rv_comments.adapter = commentsAdapter
        rv_comments.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }
}
