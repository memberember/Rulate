package tl.rulate.ru.ui.blog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import tl.rulate.ru.R

class BlogFragment : Fragment() {
    private var blogViewModel = BlogViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_blog, container, false)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requireFragmentManager().beginTransaction()
            .replace(R.id.blog_fragment_place, BlogContentFragment()).commitAllowingStateLoss()
    }
}
