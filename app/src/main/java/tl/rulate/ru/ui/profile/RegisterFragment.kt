package tl.rulate.ru.ui.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_register.*
import tl.rulate.ru.R

class RegisterFragment : Fragment(){
    private var profileViewModel = ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //инициализация данных
        reg_et_login.setText(profileViewModel.userName.value)
        reg_et_password.setText(profileViewModel.userPass.value)

        // обработчик нажатия кнопки перехода на форму регистрации
        tv_login.setOnClickListener {
            profileViewModel.currentFragment.value =
                LoginFragment()
        }

        // обработка ввода текста в поле логина
        reg_et_login.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                profileViewModel.userName.value = reg_et_login.text.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        // обработка ввода текста в поле пароля
        reg_et_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                profileViewModel.userPass.value = reg_et_password.text.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })


        btn_register.setOnClickListener {

            // проверка на пустоту полей
            if (reg_et_login.text.isEmpty() ||
                et_email.text.isEmpty() ||
                reg_et_password.text.isEmpty()
            ) {
                Toast.makeText(
                    context,
                    getText(R.string.all_fields_must_be_filled),
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            profileViewModel.register(
                login = reg_et_login.text.toString(),
                pass = reg_et_password.text.toString(),
                email = et_email.text.toString(),
                sex = getSex(),
                lang = 1
            )
        }
    }


    // функция получения пола в виде строки
    private fun getSex(): String {
        return when (rg_sex.checkedRadioButtonId) {
            1 -> "m"
            2 -> "f"
            else -> "x"
        }
    }
}