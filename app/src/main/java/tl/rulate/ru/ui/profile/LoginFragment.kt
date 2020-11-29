package tl.rulate.ru.ui.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_login.*
import tl.rulate.ru.R

class LoginFragment : Fragment() {
    private var profileViewModel = ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //инициализация данных
        log_et_login.setText(profileViewModel.userName.value)
        log_et_password.setText(profileViewModel.userPass.value)

        // обработчик нажатия кнопки перехода на форму регистрации
        tv_register.setOnClickListener {
            profileViewModel.currentFragment.value =
                RegisterFragment()
        }

        btn_login.setOnClickListener {

            // проверка на пустоту
            if (log_et_login.text.isEmpty() || log_et_password.text.isEmpty()) {
                Toast.makeText(
                    context,
                    getText(R.string.all_fields_must_be_filled),
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            profileViewModel.authorizeUser(
                log_et_login.text.toString(),
                log_et_password.text.toString()
            )
        }


        // обработка ввода текста в поле логина
        log_et_login.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    profileViewModel.userName.value = log_et_login.text.toString()
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            })

        // обработка ввода текста в поле пароля
        log_et_password.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    profileViewModel.userPass.value = log_et_password.text.toString()
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            })
    }
}