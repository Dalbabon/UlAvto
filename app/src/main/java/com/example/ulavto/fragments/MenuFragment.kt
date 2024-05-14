package com.example.ulavto.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ulavto.ContentActivity
import com.example.ulavto.R
import com.example.ulavto.adapter.ItemParts
import com.example.ulavto.adapter.PartsAdapter
import com.example.ulavto.databinding.FragmentMenuBinding
import com.example.ulavto.db.MainDb

class MenuFragment : Fragment(), PartsAdapter.Listener {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private val adapter = PartsAdapter(this)

    private var tagCat = "Всё"

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
        if (tagCat.equals("Всё")){
            observeData()
        }
        binding.tagAll.setOnClickListener{
            adapter.clear()
            tagCat = "Всё"
            observeData()
            binding.tagAll.setBackgroundResource(R.drawable.tag_sort_bg_act)
            binding.textView21.setTextColor(Color.argb(255, 36, 36, 36))
            binding.tagEngine.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView20.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagSusp.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView17.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagBatt.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView19.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagBrake.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView18.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagOther.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView16.setTextColor(Color.argb(255, 255, 255, 255))
        }
        binding.tagEngine.setOnClickListener{
            adapter.clear()
            tagCat = "Двигатель"
            observeData()
            binding.tagAll.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView21.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagEngine.setBackgroundResource(R.drawable.tag_sort_bg_act)
            binding.textView20.setTextColor(Color.argb(255, 36, 36, 36))
            binding.tagSusp.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView17.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagBatt.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView19.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagBrake.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView18.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagOther.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView16.setTextColor(Color.argb(255, 255, 255, 255))
        }
        binding.tagBatt.setOnClickListener{
            adapter.clear()
            tagCat = "Аккумулятор"
            observeData()
            binding.tagAll.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView21.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagEngine.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView20.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagSusp.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView17.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagBatt.setBackgroundResource(R.drawable.tag_sort_bg_act)
            binding.textView19.setTextColor(Color.argb(255, 36, 36, 36))
            binding.tagBrake.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView18.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagOther.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView16.setTextColor(Color.argb(255, 255, 255, 255))
        }
        binding.tagSusp.setOnClickListener{
            adapter.clear()
            tagCat = "Подвеска"
            observeData()
            binding.tagAll.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView21.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagEngine.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView20.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagSusp.setBackgroundResource(R.drawable.tag_sort_bg_act)
            binding.textView17.setTextColor(Color.argb(255, 36, 36, 36))
            binding.tagBatt.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView19.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagBrake.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView18.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagOther.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView16.setTextColor(Color.argb(255, 255, 255, 255))
        }
        binding.tagBrake.setOnClickListener{
            adapter.clear()
            tagCat = "Тормозная система"
            observeData()
            binding.tagAll.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView21.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagEngine.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView20.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagSusp.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView17.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagBatt.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView19.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagBrake.setBackgroundResource(R.drawable.tag_sort_bg_act)
            binding.textView18.setTextColor(Color.argb(255, 36, 36, 36))
            binding.tagOther.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView16.setTextColor(Color.argb(255, 255, 255, 255))
        }
        binding.tagOther.setOnClickListener{
            adapter.clear()
            tagCat = "Остальное"
            observeData()
            binding.tagAll.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView21.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagEngine.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView20.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagSusp.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView17.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagBatt.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView19.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagBrake.setBackgroundResource(R.drawable.tag_sort_bg)
            binding.textView18.setTextColor(Color.argb(255, 255, 255, 255))
            binding.tagOther.setBackgroundResource(R.drawable.tag_sort_bg_act)
            binding.textView16.setTextColor(Color.argb(255, 36, 36, 36))
        }
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
                    if(itemParts.tag.equals(tagCat)) {
                        adapter.addParts(itemParts)
                    }
                    if(tagCat.equals("Всё")) {
                        adapter.addParts(itemParts)
                    }
                }
            isDataHandledParts = false
            }
        }
    }

    override fun onClick(itemParts: ItemParts) {
        // Implement your logic here
        startActivity(Intent(requireActivity(), ContentActivity::class.java).apply {
            putExtra("item", itemParts)
        })
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