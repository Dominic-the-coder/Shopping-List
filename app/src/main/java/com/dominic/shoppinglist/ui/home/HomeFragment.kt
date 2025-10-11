package com.dominic.shoppinglist.ui.home

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.dominic.shoppinglist.databinding.FragmentHomeBinding
import com.dominic.shoppinglist.R

class HomeFragment : BaseHomeFragment() {

    override val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAdd.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddShopFragment()
            findNavController().navigate(action)
        }
        binding.fabClear.visibility = View.GONE
        binding.tvEmpty.text = getString(R.string.home_empty)
    }

    override fun getShopDetailAction(shopId: Int): NavDirections {
        return HomeFragmentDirections.actionHomeFragmentToShopDetailsFragment(shopId)
    }
}