package tl.rulate.ru.ui.indepentUi

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_reader.*
import tl.rulate.ru.R
import tl.rulate.ru.viewModels.MainViewModel

class ReaderFragment : Fragment() {

    private var bookViewModel = BookViewModel
    private var mainViewModel = MainViewModel


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

        if (mainViewModel.isUserReadChapter() || (mainViewModel.lastChapterId.value != -1)){
            tv_read_warning.visibility = View.GONE

            // запрашиваем данные по главе
            bookViewModel.chapter(mainViewModel.lastChapterId.value!!)
        }

        // слушатель на обновление данных текста
        bookViewModel.chapterText.observe(viewLifecycleOwner, Observer {
            tv_content.setText(Html.fromHtml(bookViewModel.chapterText.value))
        })



    }
}