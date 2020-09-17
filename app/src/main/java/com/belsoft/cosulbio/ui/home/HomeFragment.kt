package com.belsoft.cosulbio.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.belsoft.cosulbio.BaseFragment
import com.belsoft.cosulbio.MainActivity
import com.belsoft.cosulbio.R
import com.belsoft.cosulbio.adapters.AllProductsListAdapter
import com.belsoft.cosulbio.databinding.HomeFragmentBinding
import com.belsoft.cosulbio.utils.InjectorUtils
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fgmView = inflater.inflate(R.layout.home_fragment, container, false)
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
        provideHomeViewModelFactory(mainViewModel)

        // Use ViewModelProviders class to create / get already created QuotesViewModel
        // for this view (activity)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        // Inflate view and obtain an instance of the binding class.
        val binding: HomeFragmentBinding = HomeFragmentBinding.bind(fgmView)

        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner = this

        // Assign the component to a property in the binding class.
        binding.viewmodel = viewModel
    }

    private fun initializeUI() {
        mainViewModel.isFabButtonEnabled.value = true

        // Plug in the linear layout manager:
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        // Plug in my adapter:
        val adapter = AllProductsListAdapter((activity as MainActivity).viewModel.allProducts.value!!)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        //Set Observer for RecyclerView source list
        (activity as MainActivity).viewModel.allProducts.observe(viewLifecycleOwner, Observer { itemList ->
            // Update the cached copy of the words in the adapter.
            itemList?.let { adapter.setItems(it) }
        })

        //Set Observer for RecyclerView source list
        viewModel.updateCellLiveEvent.observe(viewLifecycleOwner, Observer { index ->
            // Update the cached copy of the words in the adapter.
            index?.let {
                adapter.notifyUpdate(index)
            }
        })

        if ((activity as MainActivity).viewModel.allProducts.value!!.isEmpty()) {
            viewModel.viewModelScope.launch {
                viewModel.getAllProducts()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mainViewModel.isFabButtonEnabled.value = false
    }
}
