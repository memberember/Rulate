package tl.rulate.ru.ui.blog

import PostRecyclerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_blog_content.*
import tl.rulate.ru.R

class BlogContentFragment : Fragment() {

    var blogViewModel = BlogViewModel
    var postsAdapter = PostRecyclerAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blog_content, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //todo добавил пагинацию

        // установка адаптера для списка постов
        rv_posts.adapter = postsAdapter
        rv_posts.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        // отслеживание изменений постов
        blogViewModel.posts.observe(viewLifecycleOwner, Observer {
            postsAdapter.set(it)
        })

    }
}