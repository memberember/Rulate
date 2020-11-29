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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_novels.*
import tl.rulate.ru.R
import tl.rulate.ru.ui.ReaderFragment
import tl.rulate.ru.viewModels.MainViewModel

class NovelsFragment : Fragment() {
    private var novelsViewModel = NovelsViewModel
    private var mainViewModel = MainViewModel
    var adapter = CardRecyclerAdapter(mutableListOf())
    var wideAdapter = WideCardRecyclerAdapter(mutableListOf())



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_novels, container, false)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        novelsViewModel.getReady()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rv_advertising.adapter = adapter
        rv_advertising.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,true)

        rv_daily_top.adapter = adapter
        rv_daily_top.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

        rv_updates.adapter = wideAdapter
        rv_updates.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)

        novelsViewModel.toastMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })


        novelsViewModel.novels.observe(viewLifecycleOwner, Observer {
            adapter.set(it)
            wideAdapter.set(it)
        })

        adapter.onItemClick = { novel ->
            mainViewModel.lastBookId.value = novel.book_id
            mainViewModel.lastChapterId.value = novel.id
            mainViewModel.currentFragment.value =
                ReaderFragment()

            // do something with your item
            Log.d("TAG", novel.t_title)
        }
    }
}
