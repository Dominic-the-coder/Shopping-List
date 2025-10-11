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
        binding.fabAdd.visibility = View.GONE

        binding.mbClear.setOnClickListener {
            viewModel.clearCompleted()
        }
        binding.mbClear.visibility = View.VISIBLE
        binding.tvEmpty.text = getString(R.string.complete_empty)
    }

    override fun getShopDetailAction(shopId: Int): NavDirections {
        return CompleteShopFragmentDirections.actionCompleteShopFragmentToShopDetailsFragment(shopId)
    }

}