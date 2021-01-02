package tl.rulate.ru.ui

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_reader.*
import tl.rulate.ru.R
import tl.rulate.ru.ui.novels.NovelsViewModel

class ReaderFragment : Fragment() {

    var novelsViewModel = NovelsViewModel
    var readerViewModel = ReaderViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reader, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // слушатель на обновление данных текста
        readerViewModel.text.observe(viewLifecycleOwner, Observer {
            tv_content.setText(Html.fromHtml(readerViewModel.text.value))
        })

        // запрашиваем данные по главе
        readerViewModel.chapter(novelsViewModel.lastChapterId.value!!)

    }
}