package com.example.jamsostekapps.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jamsostekapps.databinding.FragmentHomeBinding
import com.example.jamsostekapps.utils.DummyDataHelper.getListService

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var saerviceAdapter: ServiceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        saerviceAdapter = ServiceAdapter()
        val layoutManager = GridLayoutManager(
            activity,
            4,
            GridLayoutManager.VERTICAL,
            false
        )
        with(binding.rvLayanan) {
            adapter = saerviceAdapter
            this.layoutManager = layoutManager
        }
        saerviceAdapter.setData(getListService())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}