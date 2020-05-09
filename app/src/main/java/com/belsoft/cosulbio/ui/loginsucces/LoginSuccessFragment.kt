package com.belsoft.cosulbio.ui.loginsucces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.belsoft.cosulbio.BaseFragment
import com.belsoft.cosulbio.R
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginSuccessFragment : BaseFragment() {

    lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onResume() {
        super.onResume()
        localScope.launch {
            try {
                delay(5000)
                navController.navigate(R.id.action_loginSuccessFragment_to_homeFragment)
            }
            catch (e: CancellationException) {
                println("Coroutine cancelled - ${e.message}")
            }
        }
    }

}
