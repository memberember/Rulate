package tl.rulate.ru.ui.novels

import CardRecyclerAdapter
import WideCardRecyclerAdapter
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
import tl.rulate.ru.R
import tl.rulate.ru.ui.BookFragment
import tl.rulate.ru.ui.ReaderFragment

class NovelsContentFragment : Fragment() {
    private var novelsViewModel = NovelsViewModel

    // адаптеры рекламы, новинок и последних обновлений
    var adsAdapter = CardRecyclerAdapter(mutableListOf())
    var newBooksAdapter = CardRecyclerAdapter(mutableListOf())
    var wideAdapter = WideCardRecyclerAdapter(mutableListOf())


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

        // todo хрень для тестов
//        novelsViewModel.lastBookId.value = 25507
//        novelsViewModel.currentFragment.value = BookFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // привязываем адаптеры и устанавливаем им параметры
        rv_advertising.adapter = adsAdapter
        rv_advertising.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, true)

        rv_daily_top.adapter = newBooksAdapter
        rv_daily_top.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        rv_updates.adapter = wideAdapter
        rv_updates.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        novelsViewModel.toastMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })


        // ставим слушатели на изменение данных какого либо адаптера
        novelsViewModel.novels.observe(viewLifecycleOwner, Observer {
            wideAdapter.set(it)
        })

        novelsViewModel.newBooks.observe(viewLifecycleOwner, Observer {
            newBooksAdapter.set(it)
        })

        novelsViewModel.ads.observe(viewLifecycleOwner, Observer {
            adsAdapter.set(it)
        })

        // устанавливаем слушатели на элементы адаптеров
        newBooksAdapter.onItemClick = { novel ->
            novelsViewModel.lastChapterId.value = novel.id
            novelsViewModel.currentFragment.value =
                ReaderFragment()
        }

        adsAdapter.onItemClick = { novel ->
            novelsViewModel.lastChapterId.value = novel.id
            novelsViewModel.currentFragment.value =
                ReaderFragment()
        }

        wideAdapter.onItemClick = { novel ->
            novelsViewModel.lastBookId.value = novel.book_id
            novelsViewModel.lastChapterId.value = novel.id
            novelsViewModel.currentFragment.value =
                ReaderFragment()
        }
    }
}
