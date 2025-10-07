package com.dominic.shoppinglist.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dominic.shoppinglist.data.enums.SortBy
import com.dominic.shoppinglist.data.utils.Constant
import com.dominic.shoppinglist.databinding.FragmentHomeBinding
import com.dominic.shoppinglist.ui.adapter.ShopsAdapter
import com.dominic.shoppinglist.ui.sort.SortDialogFragment
import kotlinx.coroutines.launch

abstract class BaseHomeFragment : Fragment() {

    protected lateinit var binding: FragmentHomeBinding
    protected lateinit var adapter: ShopsAdapter
    protected abstract val viewModel: BaseHomeViewModel
    protected var currentOrder = SortBy.AZ


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setNavigation()

        lifecycleScope.launch {
            viewModel.shops.collect {
                adapter.setShops(it)
                binding.llEmpty.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            }
        }

        setFragmentResultListener(Constant.MANAGE_SHOP) { _, _ -> viewModel.refresh() }
    }

    fun setupAdapter() {
        adapter = ShopsAdapter(
            emptyList(),
            onPress = {
                navigateToDetails(it.id!!)
            }
        )

        binding.rvShop.adapter = adapter
        binding.rvShop.layoutManager = LinearLayoutManager(this.context)
    }

    protected fun navigateToDetails(shopId: Int) {
        val action = getShopDetailAction(shopId)
        findNavController().navigate(action)
    }

    protected abstract fun getShopDetailAction(shopId: Int): NavDirections

    fun setSort(sortBy: SortBy) {
        currentOrder = sortBy
        viewModel.setSorting(sortBy)
    }

    fun setNavigation() {
        binding.ivSort.setOnClickListener {
            val dialog = SortDialogFragment(currentOrder) { sortBy ->
                setSort(sortBy)
            }
            dialog.show(parentFragmentManager, Constant.SORTING_DIALOG)
        }
    }
}