package com.dominic.shoppinglist.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.dominic.shoppinglist.R

class CompleteShopFragment : BaseHomeFragment() {

    override val viewModel: CompleteShopViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAdd.setOnClickListener {
            val action = CompleteShopFragmentDirections.actionCompleteShopFragmentToAddShopFragment()
            findNavController().navigate(action)
        }
        binding.tvEmpty.text = getString(R.string.complete_empty)
    }

    override fun getShopDetailAction(shopId: Int): NavDirections {
        return CompleteShopFragmentDirections.actionCompleteShopFragmentToShopDetailsFragment(shopId)
    }

}