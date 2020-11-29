package tl.rulate.ru.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import tl.rulate.ru.R
import tl.rulate.ru.ui.UserProfileFragment
import tl.rulate.ru.viewModels.MainViewModel

class ProfileFragment : Fragment() {
    // todo перетащить метод getUser

    var mainViewModel = MainViewModel
    private var profileViewModel = ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.d(
            "DebugonCreate", "it"
        )
        // считывание данных с sharedpref
        profileViewModel.dataUser.value = mainViewModel.sharedPref.user
        profileViewModel.myUserId.value = mainViewModel.myUserId.value

        Log.d(
            "DebugProfileFragment",
            mainViewModel.sharedPref.user.id.toString() + " " + profileViewModel.myUserId.value.toString()
        )

        // слушатель смены фрагментов
        profileViewModel.currentFragment.observe(this, Observer {
            it?.let {
                Log.d("DebugProfileFragmenCurF", it.toString())
                inflateToFragmentPlace(it)
            }
        })

        Log.d(
            "DebugProfileFragment1", "it"
        )
        // если пользователь уже был авторизован, то открываем профиль иначе авторизацию
        if (isUserAuthorized()) {
            profileViewModel.currentFragment.value = UserProfileFragment()
        } else {
            profileViewModel.currentFragment.value = LoginFragment()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // слушатель на изменение данных текущего пользователя
        profileViewModel.dataUser.observe(viewLifecycleOwner, Observer {
            Log.d("DebugProfileFragmenVLO", it.id.toString())

            // инфлейтим фрагмент профиля
            if (isUserAuthorized())
                profileViewModel.currentFragment.value = UserProfileFragment()

            // если полученный пользователь это я, то сохраняем в кеш
            if (it.id == profileViewModel.myUserId.value) {
                mainViewModel.sharedPref.saveUser(it)
            }
        })

        // если пользователь логинится, то данные передаются в mainViewModel
        profileViewModel.myUserId.observe(viewLifecycleOwner, Observer {
            if (isUserAuthorized()) {
                Log.d(
                    "DebugProfilemyUserId",
                    it.toString() + profileViewModel.myUserId.value.toString()
                )
                mainViewModel.myUserId.value = profileViewModel.myUserId.value
                profileViewModel.getUser(it)
            }
        })


        //todo иногда срабатывает при загрузке активити
        // тостер
//        profileViewModel.toastMessage.observe(viewLifecycleOwner, Observer {
//            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
//        })
    }

    private fun inflateToFragmentPlace(fragment: Fragment) {
        this.requireFragmentManager().beginTransaction()
            .replace(R.id.profile_fragment_place, fragment).commitAllowingStateLoss()
    }

    private fun isUserAuthorized(): Boolean {
        if (profileViewModel.myUserId.value != -1)
            return true
        return false
    }
}