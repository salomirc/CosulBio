package com.belsoft.cosulbio

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.belsoft.cosulbio.utils.InjectorUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class BaseFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel
    lateinit var fgmView: View
    val localScope: CoroutineScope = MainScope()
    var isRunning: Boolean = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Get the QuotesViewModelFactory with all of it's dependencies constructed
        val factory = InjectorUtils.provideMainViewModelFactory()

        // Use ViewModelProviders class to create / get already created QuotesViewModel
        // for this view (activity)
        mainViewModel = ViewModelProvider(requireActivity(), factory)
            .get(MainViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        localScope.cancel()
    }
}