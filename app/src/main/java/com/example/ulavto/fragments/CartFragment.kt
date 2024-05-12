package com.example.ulavto.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ulavto.R
import com.example.ulavto.adapter.ItemParts
import com.example.ulavto.adapter.PartsAdapter
import com.example.ulavto.databinding.FragmentCartBinding
import com.example.ulavto.db.MainDb

class CartFragment : Fragment(), PartsAdapter.Listener{

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val adapter = PartsAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
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
        var SummCost = 0

        val db = MainDb.getDb(requireContext())
        db.getDao().getCartItems().asLiveData().observe(viewLifecycleOwner) {
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

                    SummCost += item.cost

                    if(adapter.itemCount.equals(0))
                        binding.textView.visibility = View.VISIBLE
                    else
                        binding.textView.visibility = View.INVISIBLE
                }
                isDataHandledParts = false

                binding.textView8.setText(SummCost.toString() + " ₽")
            }
        }
        binding.cartbutton.setOnClickListener{
            binding.textView9.setText("Заказ оформлен")
            binding.textView9.setTextColor(Color.argb(255, 255, 255, 255))
            binding.cartbutton.setBackgroundResource(R.drawable.button_bg_act)
        }
    }

    override fun onClick(itemParts: ItemParts) {
        // Implement your logic here
    }

    companion object {
        @JvmStatic
        fun newInstance() = CartFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}