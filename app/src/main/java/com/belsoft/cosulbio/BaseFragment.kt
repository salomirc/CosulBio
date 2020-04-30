package com.belsoft.cosulbio

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.belsoft.cosulbio.utils.InjectorUtils
import com.belsoft.cosulbio.utils.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class BaseFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel
    lateinit var fgmView: View
    val localScope: CoroutineScope = MainScope()
    var isRunning: Boolean = false

    override fun onAttach(context: Context) {
        Log.d(TAG, "${javaClass.simpleName} onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "${javaClass.simpleName} onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Get the QuotesViewModelFactory with all of it's dependencies constructed
        val factory = InjectorUtils.getInstance(requireActivity().applicationContext).provideMainViewModelFactory()

        // Use ViewModelProviders class to create / get already created QuotesViewModel
        // for this view (activity)
        mainViewModel = ViewModelProvider(requireActivity(), factory).get(MainViewModel::class.java)
    }

    override fun onDestroyView() {
        Log.d(TAG, "${javaClass.simpleName} onDestroyView")
        super.onDestroyView()
        localScope.cancel()
    }

    override fun onDestroy() {
        Log.d(TAG, "${javaClass.simpleName} onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d(TAG, "${javaClass.simpleName} onDetach")
        super.onDetach()
    }
}