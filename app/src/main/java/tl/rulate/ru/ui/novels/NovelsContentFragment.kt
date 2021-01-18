package tl.rulate.ru.ui.novels

import CRTitleAdapter
import CRWideLastUpdatesAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_novels_content.*
import tl.rulate.ru.Constants
import tl.rulate.ru.R
import tl.rulate.ru.viewModels.MainViewModel

class NovelsContentFragment : Fragment() {
    private var novelsViewModel = NovelsViewModel
    private var mainViewModel = MainViewModel

    // адаптеры рекламы, новинок и последних обновлений
    var adsAdapter = CRTitleAdapter(mutableListOf())
    var newBooksAdapter = CRTitleAdapter(mutableListOf())
    var lastUpdatesAdapter = CRWideLastUpdatesAdapter(mutableListOf())


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_novels_content, container, false)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // запрашиваем данные с сайта
        novelsViewModel.getReady()
        novelsViewModel.newBooks()
        novelsViewModel.ads()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(Constants.DEBUG+"NCF", object : Any() {}.javaClass.enclosingMethod?.name!!)



        // привязываем адаптеры и устанавливаем им параметры
        rv_advertising.adapter = adsAdapter
        rv_advertising.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, true)

        rv_new_books.adapter = newBooksAdapter
        rv_new_books.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        rv_updates.adapter = lastUpdatesAdapter
        rv_updates.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        novelsViewModel.toastMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })


        // ставим слушатели на изменение данных какого либо адаптера
        novelsViewModel.novels.observe(viewLifecycleOwner, Observer {
            lastUpdatesAdapter.set(it)
        })

        novelsViewModel.newBooks.observe(viewLifecycleOwner, Observer {
            newBooksAdapter.set(it)
        })

        novelsViewModel.ads.observe(viewLifecycleOwner, Observer {
            adsAdapter.set(it)
            Log.d(Constants.DEBUG, "adsobserve")

        })

        // устанавливаем слушатели на элементы адаптеров
        newBooksAdapter.onItemClick = { novel ->
            mainViewModel.lastBookId.value = novel.id
        }

        adsAdapter.onItemClick = { novel ->
            mainViewModel.lastBookId.value = novel.id
        }

        lastUpdatesAdapter.onItemClick = { novel ->
            mainViewModel.lastBookId.value = novel.book_id
        }
    }
}
