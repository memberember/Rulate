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
import tl.rulate.ru.viewModels.MainViewModel
import tl.rulate.ru.viewModels.ReaderViewModel

class ReaderFragment : Fragment() {

    var mainViewModel = MainViewModel
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

        readerViewModel.text.observe(viewLifecycleOwner, Observer {
            tv_content.setText(Html.fromHtml(readerViewModel.text.value))
        })

        backbtn.setOnClickListener {
            readerViewModel.getBook(1134025,mainViewModel.lastBookId.value!!)
        }

        readerViewModel.getBook(1148726,mainViewModel.lastBookId.value!!)

    }
}