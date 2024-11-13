package com.belsoft.cosulbio.ui.cosul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.belsoft.cosulbio.R

class CosulMeuFragment : Fragment() {

    companion object {
        fun newInstance() = CosulMeuFragment()
    }

    private lateinit var viewModel: CosulMeuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cosul_meu_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CosulMeuViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
