package tl.rulate.ru.ui.novels

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import tl.rulate.ru.Constants.DEBUG
import tl.rulate.ru.R

class NovelsFragment : Fragment() {
    private var novelsViewModel = NovelsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Log.d(DEBUG, object : Any() {}.javaClass.enclosingMethod?.name!!)
        val view = inflater.inflate(R.layout.fragment_novels, container, false)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        novelsViewModel.currentFragment.observe(this, Observer {
            it?.let {
                this.requireFragmentManager().beginTransaction()
                    .replace(R.id.novels_fragment_place, it).addToBackStack("novelRead")
                    .commitAllowingStateLoss()
            }
        })
    }



    override fun onResume() {
        super.onResume()

//        this.requireFragmentManager().beginTransaction()
//            .replace(R.id.novels_fragment_place, novelsViewModel.currentFragment.value?:NovelsContentFragment()).commitAllowingStateLoss()
        this.requireFragmentManager().beginTransaction()
            .replace(R.id.novels_fragment_place, NovelsContentFragment()).commitAllowingStateLoss()
    }
}
