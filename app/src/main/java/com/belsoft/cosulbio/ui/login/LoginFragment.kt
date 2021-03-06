package com.belsoft.cosulbio.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.belsoft.cosulbio.BaseFragment
import com.belsoft.cosulbio.MainActivity
import com.belsoft.cosulbio.R
import com.belsoft.cosulbio.databinding.LoginFragmentBinding
import com.belsoft.cosulbio.models.LoginFormItemModel
import com.belsoft.cosulbio.utils.InjectorUtils
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment() {

    private lateinit var binding: LoginFragmentBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var loginEditTextList: List<EditText>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fgmView = inflater.inflate(R.layout.login_fragment, container, false)
        return fgmView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setArchitectureComponents()
        initializeUI()
    }

    private fun setArchitectureComponents() {

        // Get the LoginViewModelFactory with all of it's dependencies constructed
        val factory = InjectorUtils.getInstance(requireActivity().applicationContext).
                                                    provideLoginViewModelFactory(mainViewModel)

        // Use ViewModelProviders class to create / get already created LoginViewModel
        // for this view (activity)
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        // Inflate view and obtain an instance of the binding class.
        binding = LoginFragmentBinding.bind(fgmView)

        // Specify the current fragment as the lifecycle owner.
        binding.lifecycleOwner = this

        // Assign the component to a property in the binding class.
        binding.viewmodel = viewModel
    }

    private fun initializeUI() {

        //Version I : using DataBinding Library and LiveData to send the event in the ViewModel
        //            then use LiveData observe() to subscribe to notifications from LiveData for ViewModel to View
        //            communication

        viewModel.hideKeyboardSafeLiveEvent.observe(this, Observer {
            hideKeyboardSafe()
            viewModel.viewModelScope.launch {
                viewModel.onLoginButtonClickContinuation()
            }
        })


        // Version II : using Kotlin Synthetics to access the UI elements from code behind
//        loginButton.setOnClickListener {
//            hideKeyboardSafe()
//            viewModel.viewModelScope.launch {
//                viewModel.onLoginButtonClickContinuation()
//            }
//        }


        // Version III: using DataBinding Library to access the UI elements from code behind
//        binding.loginButton.setOnClickListener {
//            hideKeyboardSafe()
//            viewModel.viewModelScope.launch {
//                viewModel.onLoginButtonClickContinuation()
//            }
//        }

        val loginItemList = mutableListOf(
            LoginFormItemModel(requireContext().getString(R.string.username)),
            LoginFormItemModel(requireContext().getString(R.string.password))
        )

        if (viewModel.loginList.isEmpty()){
            viewModel.loginList = loginItemList
            viewModel.logins.value = viewModel.loginList
        }

        loginEditTextList = listOf(usernameEditText, passwordEditText)
        for (item in loginEditTextList){
            item.doAfterTextChanged {
                viewModel.validateLoginField(it.toString(), loginEditTextList.indexOf(item))
            }
        }
    }

     private fun hideKeyboardSafe() {
        if (MainActivity.isKeyboardOnScreen()) {
            MainActivity.hideSoftKeyboard(loginRootLayout.findFocus())
            loginRootLayout.findFocus().clearFocus()
        }
    }
}
