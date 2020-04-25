package com.belsoft.cosulbio.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.belsoft.cosulbio.BaseFragment
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

        // Inflate view and obtain an instance of the binding class.
        val binding: LoginFragmentBinding = LoginFragmentBinding.bind(fgmView)

        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner = this

        // Assign the component to a property in the binding class.
        binding.viewmodel = viewModel
    }

    private fun initializeUI() {
        //SingleEvent received from ViewModel
        viewModel.toastMessage.observe(viewLifecycleOwner, Observer { message ->
            // Update the cached copy of the words in the adapter.
            message?.let {
                mainViewModel.toastMessage.value = it
            }
        })
    }

}
