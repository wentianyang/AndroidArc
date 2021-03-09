package com.android.live

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.base.recyclerview.BaseItemUiViewModel
import com.android.live.databinding.FragmentHomeBinding
import com.android.live.vm.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var dataBinding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeAdapter = HomeAdapter(requireContext())
        dataBinding.homeRecyclerView.layoutManager = LinearLayoutManager(context)
        dataBinding.homeRecyclerView.adapter = homeAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.homeData.observe(viewLifecycleOwner) {
            Log.d("HomeFragment", "onActivityCreated---------: ${it.toString()}")
            homeAdapter.data = it as MutableList<BaseItemUiViewModel>
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}