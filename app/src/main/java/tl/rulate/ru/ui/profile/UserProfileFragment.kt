package tl.rulate.ru.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user_profile.*
import tl.rulate.ru.R
import tl.rulate.ru.ui.profile.ProfileViewModel

class UserProfileFragment : Fragment() {
    private var profileViewModel = ProfileViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // слушатель на изменение данных текущего пользователя
        profileViewModel.dataUser.observe(viewLifecycleOwner, Observer {
            profileDataInitialize()
        })


//        // кнопка выхода из логина
//        tv_login.setOnClickListener {
//            mainViewModel.sharedPref.clear()
//
//            //todo баг с тем что не срабатывает ошибка
//            profileViewModel.myUserId.value = -1
//            profileViewModel.currentFragment.value = LoginFragment()
//        }
    }

    // инициализация данных
    private fun profileDataInitialize() {
        tv_status_value.text = profileViewModel.dataUser.value!!.status
        tv_sex_value.text = profileViewModel.dataUser.value!!.sex
        tv_books_count_value.text = profileViewModel.dataUser.value!!.books_count.toString()
        tv_translated_value.text = profileViewModel.dataUser.value!!.translated
        tv_karma_value.text = profileViewModel.dataUser.value!!.karma.toString()
        tv_cdate_value.text = profileViewModel.dataUser.value!!.cdate
        tv_name.text = profileViewModel.dataUser.value!!.name
        tv_country_value.text = profileViewModel.dataUser.value!!.country
        tv_city_value.text = profileViewModel.dataUser.value!!.city
        tv_birthday_value.text = profileViewModel.dataUser.value!!.birthday
        tv_confirmed_value.text = profileViewModel.dataUser.value!!.confirmed.toString()
        tv_comments_count_value.text = profileViewModel.dataUser.value!!.comments_count.toString()
        tv_login.text = profileViewModel.dataUser.value!!.login

        if (profileViewModel.dataUser.value!!.avatar_big.isNotBlank()) {

            Picasso.with(context)
                .load(profileViewModel.dataUser.value!!.avatar_big)
                .placeholder(R.drawable.ic_android_black_24dp)
                .error(R.drawable.ic_android_black_24dp)
                .into(iv_avatar)
        }
    }
}