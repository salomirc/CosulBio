package com.belsoft.cosulbio.ui.logout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.belsoft.cosulbio.BaseFragment
import com.belsoft.cosulbio.R
import com.belsoft.cosulbio.databinding.LogoutFragmentBinding
import com.belsoft.cosulbio.utils.InjectorUtils

class LogoutFragment : BaseFragment() {

    private lateinit var viewModel: LogoutViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fgmView = inflater.inflate(R.layout.logout_fragment, container, false)
        return fgmView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setArchitectureComponents()
        initializeUI()
    }

    private fun setArchitectureComponents() {

        // Get the QuotesViewModelFactory with all of it's dependencies constructed
        val factory = InjectorUtils.getInstance(requireActivity().applicationContext).
                                                        provideLogoutViewModelFactory(mainViewModel)

        // Use ViewModelProviders class to create / get already created QuotesViewModel
        // for this view (activity)
        viewModel = ViewModelProvider(this, factory).get(LogoutViewModel::class.java)

        // Inflate view and obtain an instance of the binding class.
        val binding: LogoutFragmentBinding = LogoutFragmentBinding.bind(fgmView)

        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner = this

        // Assign the component to a property in the binding class.
        binding.viewmodel = viewModel
    }

    private fun initializeUI() {

    }

}
