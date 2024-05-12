package com.example.ulavto.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ulavto.adapter.ItemParts
import com.example.ulavto.adapter.PartsAdapter
import com.example.ulavto.databinding.FragmentMenuBinding
import com.example.ulavto.db.MainDb

class MenuFragment : Fragment(), PartsAdapter.Listener {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private val adapter = PartsAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeData()
    }

    private fun setupRecyclerView() {
        binding.rcView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rcView.adapter = adapter
    }

    private fun observeData() {
        var isDataHandledParts = true

        val db = MainDb.getDb(requireContext())
        db.getDao().getAllItems().asLiveData().observe(viewLifecycleOwner) {
            if(isDataHandledParts){
                it.forEach { item ->
                    val itemParts = ItemParts(
                        item.id,
                        item.tag,
                        item.nameN,
                        item.description,
                        item.cost,
                        item.avatarUrl,
                        item.favorite,
                        item.cart
                    )
                    adapter.addParts(itemParts)
                }
            isDataHandledParts = false
            }
        }
    }

    override fun onClick(itemParts: ItemParts) {
        // Implement your logic here
    }

    companion object {

        @JvmStatic
        fun newInstance() = MenuFragment()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}