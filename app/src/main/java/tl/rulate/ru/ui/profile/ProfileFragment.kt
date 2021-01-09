package tl.rulate.ru.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import tl.rulate.ru.R
import tl.rulate.ru.data.SharedPrefData
import tl.rulate.ru.ui.UserProfileFragment
import tl.rulate.ru.viewModels.MainViewModel

class ProfileFragment : Fragment() {

    var mainViewModel = MainViewModel
    var profileViewModel = ProfileViewModel

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

        // слушатель смены фрагментов
        profileViewModel.currentFragment.observe(this, Observer {
            it?.let {
                inflateToFragmentPlace(it)
            }
        })

        openRequiredForm()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.isLogoutPressed.observe(viewLifecycleOwner, Observer {
            it.let {
                profileViewModel.dataUser = MutableLiveData()
                profileViewModel.myUserToken = MutableLiveData()
                profileViewModel.myUserId = MutableLiveData()
                mainViewModel.isLogoutPressed = MutableLiveData()
                openRequiredForm()
            }
        })

        // слушатель изменения dataUser
        profileViewModel.myUserToken.observe(viewLifecycleOwner, Observer {

            it.let {
                mainViewModel.sharedPref.saveUser(
                    SharedPrefData(
                        id = profileViewModel.myUserId.value!!,
                        token = profileViewModel.myUserToken.value!!
                    )
                )

                openRequiredForm()
            }
        })
    }

    private fun inflateToFragmentPlace(fragment: Fragment) {
        this.requireFragmentManager().beginTransaction()
            .replace(R.id.profile_fragment_place, fragment).commitAllowingStateLoss()
    }

    // функция проверки авторизованности пользователя
    private fun isUserAuthorized(): Boolean {
        if (mainViewModel.sharedPref.user.id != -1)
            return true
        return false
    }

    // функция открытия необходимой формы
    private fun openRequiredForm() {
        // если пользователь уже был авторизован, то открываем профиль иначе авторизацию
        if (isUserAuthorized()) {
            profileViewModel.getUser(mainViewModel.sharedPref.user.id)
            profileViewModel.currentFragment.value = UserProfileFragment()
        } else {
            profileViewModel.currentFragment.value = LoginFragment()
        }
    }
}