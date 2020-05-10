package com.belsoft.cosulbio.ui.loginsucces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.belsoft.cosulbio.BaseFragment
import com.belsoft.cosulbio.R
import com.belsoft.cosulbio.databinding.FragmentLoginSuccessBinding
import com.belsoft.cosulbio.utils.InjectorUtils
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginSuccessFragment : BaseFragment() {

    lateinit var navController: NavController
    private lateinit var viewModel: LoginSuccessViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fgmView = inflater.inflate(R.layout.fragment_login_success, container, false)
        return fgmView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setArchitectureComponents()
    }

    private fun setArchitectureComponents() {

        // Get the QuotesViewModelFactory with all of it's dependencies constructed
        val factory = InjectorUtils.getInstance(requireActivity().applicationContext).
        provideLoginSuccessViewModelFactory(mainViewModel)

        // Use ViewModelProviders class to create / get already created QuotesViewModel
        // for this view (activity)
        viewModel = ViewModelProvider(this, factory).get(LoginSuccessViewModel::class.java)

        // Inflate view and obtain an instance of the binding class.
        val binding: FragmentLoginSuccessBinding = FragmentLoginSuccessBinding.bind(fgmView)

        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner = this

        // Assign the component to a property in the binding class.
        binding.viewmodel = viewModel
    }


    override fun onResume() {
        super.onResume()
        viewModel.navigateToHomeFragment()
    }

}
