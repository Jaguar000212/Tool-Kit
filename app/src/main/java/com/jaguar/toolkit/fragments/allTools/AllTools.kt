package com.jaguar.toolkit.fragments.allTools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.jaguar.toolkit.adapters.ToolsRecyclerAdapter
import com.jaguar.toolkit.data.ToolList
import com.jaguar.toolkit.databinding.FragmentAllToolsBinding
import com.jaguar.toolkit.objects.Tool

class AllTools : Fragment() {
    private var _binding: FragmentAllToolsBinding? = null
    private val binding get() = _binding!!
    private val toolList: List<Tool<*>> = ToolList.getTools()
    private lateinit var adapter: ToolsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllToolsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.allToolsView.apply {
            layoutManager = GridLayoutManager(context, 2)
            itemAnimator = DefaultItemAnimator()
            adapter = ToolsRecyclerAdapter(context, toolList).also { adapter = it }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = AllTools()
    }
}