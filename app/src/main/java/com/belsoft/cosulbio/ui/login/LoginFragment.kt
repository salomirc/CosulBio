package com.belsoft.cosulbio.ui.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.belsoft.cosulbio.BaseFragment
import com.belsoft.cosulbio.MainViewModel

import com.belsoft.cosulbio.R
import com.belsoft.cosulbio.databinding.LoginFragmentBinding
import com.belsoft.cosulbio.utils.InjectorUtils

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

        viewModel.mainViewModel = mainViewModel

        // Inflate view and obtain an instance of the binding class.
        val binding: LoginFragmentBinding = LoginFragmentBinding.bind(fgmView)

        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner = this

        // Assign the component to a property in the binding class.
        binding.viewmodel = viewModel
    }

    private fun initializeUI() {
        viewModel.displayToast(R.string.login)
    }

}
