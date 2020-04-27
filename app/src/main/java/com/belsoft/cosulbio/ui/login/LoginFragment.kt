package com.belsoft.cosulbio.ui.login

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.belsoft.cosulbio.BaseFragment
import com.belsoft.cosulbio.R
import com.belsoft.cosulbio.adapters.LoginListAdapter
import com.belsoft.cosulbio.databinding.LoginFragmentBinding
import com.belsoft.cosulbio.models.FormItemModel
import com.belsoft.cosulbio.utils.InjectorUtils
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment() {

    private lateinit var viewModel: LoginViewModel

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
        // Get the QuotesViewModelFactory with all of it's dependencies constructed
        val factory = InjectorUtils.provideLoginViewModelFactory()

        // Use ViewModelProviders class to create / get already created QuotesViewModel
        // for this view (activity)
        viewModel = ViewModelProvider(this, factory)
            .get(LoginViewModel::class.java)

        // Inflate view and obtain an instance of the binding class.
        val binding: LoginFragmentBinding = LoginFragmentBinding.bind(fgmView)

        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner = this

        // Assign the component to a property in the binding class.
        binding.viewmodel = viewModel
    }

    private fun initializeUI() {

        // Plug in the linear layout manager:
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        // Plug in my adapter:
        val adapter = LoginListAdapter(requireActivity(), viewModel.logins.value!!, viewModel)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)


        //Set Observer for RecyclerView source list
        viewModel.logins.observe(viewLifecycleOwner, Observer { loginList ->
            // Update the cached copy of the words in the adapter.
            loginList?.let { adapter.setItems(it) }
        })

        //SingleEvent received from ViewModel
        viewModel.toastMessage.observe(viewLifecycleOwner, Observer { message ->
            // Update the cached copy of the words in the adapter.
            message?.let {
                mainViewModel.toastMessage.value = it
            }
        })

        loginButton.setOnClickListener {
            if (isRunning) return@setOnClickListener
            isRunning = true

            localScope.launch {
//                when {
//                    usernameEditText.text.toString().isBlank() -> {
//                        showLeyboardSafe(usernameEditText)
//                    }
//                    passwordEditText.text.toString().isBlank() -> {
//                        showLeyboardSafe(passwordEditText)
//                    }
//                    else -> {
//                        if (MainActivity.isKeyboardOnScreen()){
//                            MainActivity.hideSoftKeyboard(rootLayout.findFocus())
//                            rootLayout.findFocus().clearFocus()
//                        }
//                        viewModel.isVisibleSearchSelectProgessBar.value = true
//                        delay(2000)
//                        viewModel.isVisibleSearchSelectProgessBar.value = false
//                    }
//                }
                isRunning = false
            }
        }

        val itemList = mutableListOf(
            FormItemModel(R.string.username, inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL),
            FormItemModel(R.string.password, inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD , imeOptions = EditorInfo.IME_ACTION_DONE)
        )

        if (viewModel.loginList.size == 0){
            viewModel.loginList =  itemList
            viewModel.logins.value = viewModel.loginList
        }
    }

//    private suspend fun showLeyboardSafe(view: View) {
//        if (!MainActivity.isKeyboardOnScreen()) {
//            MainActivity.showSoftKeyboard(view)
//            delay(200)
//        }
//        else{
//            view.requestFocus()
//        }
//    }

}
