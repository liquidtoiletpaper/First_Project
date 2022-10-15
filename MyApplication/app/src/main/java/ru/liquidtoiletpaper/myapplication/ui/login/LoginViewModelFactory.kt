package ru.liquidtoiletpaper.myapplication.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.liquidtoiletpaper.myapplication.data.LoginDataSource
import ru.liquidtoiletpaper.myapplication.data.LoginRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}