package tl.rulate.ru.ui.search

import SearchRecyclerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*
import tl.rulate.ru.R
import tl.rulate.ru.viewModels.MainViewModel

class SearchFragment : Fragment() {
    val searchViewModel = SearchViewModel
    var searchAdapter = SearchRecyclerAdapter(mutableListOf())
    var mainViewModel = MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_search, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // установка адаптера для списка постов
        rv_search_result.adapter = searchAdapter
        rv_search_result.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)


        btn_search.setOnClickListener {
            searchViewModel.search.value = et_search.text.toString()
        }

        searchViewModel.search.observe(viewLifecycleOwner, Observer {
            searchViewModel.searchBooks()
        })

        searchViewModel.searchResults.observe(viewLifecycleOwner, Observer {
            searchAdapter.set(it)
        })

        searchAdapter.onItemClick = { novel ->
            mainViewModel.lastBookId.value = novel.id
        }
    }
}
