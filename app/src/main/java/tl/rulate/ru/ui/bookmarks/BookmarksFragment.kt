package tl.rulate.ru.ui.bookmarks

import BookmarkRecyclerAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_bookmarks.*
import tl.rulate.ru.R
import tl.rulate.ru.viewModels.MainViewModel

class BookmarksFragment : Fragment() {

    var bookmarksViewModel = BookmarksViewModel
    var mainViewModel = MainViewModel
    var bookmarksAdapter = BookmarkRecyclerAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bookmarks, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (mainViewModel.isUserAuthorized()) {
            tv_bookmarks_warning.visibility = View.GONE

            mainViewModel.sharedPref.user.token.let {
                bookmarksViewModel.bookmarks(it)
            }
        }

        //todo добавил пагинацию

        // установка адаптера для списка постов
        rv_bookmarks.adapter = bookmarksAdapter
        rv_bookmarks.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        // отслеживание изменений постов
        bookmarksViewModel.bookmarks.observe(viewLifecycleOwner, Observer {
            bookmarksAdapter.set(it)
        })

        bookmarksAdapter.onItemClick = { novel ->
            mainViewModel.lastBookId.value = novel.book_id
        }

    }
}