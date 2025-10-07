package com.dominic.shoppinglist.ui.manage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dominic.shoppinglist.R
import com.dominic.shoppinglist.data.utils.Constant
import com.dominic.shoppinglist.databinding.ManageItemLayoutBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

abstract class BaseManageFragment : Fragment() {
    protected lateinit var binding: ManageItemLayoutBinding
    protected abstract val viewModel: BaseManageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ManageItemLayoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.finish.collect {
                navigateBack()
            }
        }

        // If it receives a SharedFlow emit of error, it will show a snackbar
        lifecycleScope.launch {
            viewModel.error.collect {
                val message = when (it) {
                    "NO_NAME" -> getString(R.string.no_name)
                    "NO_DESCRIPTION" -> getString(R.string.no_desc)
                    else -> getString(R.string.error)
                }
                showError(message)
            }
        }

        // The back button on the toolbar. Navigates back
        binding.mtManage.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun showError(msg: String) {
        val snackbar = Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG)
        snackbar.setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.red)).show()
    }

    fun navigateBack() {
        setFragmentResult(Constant.MANAGE_SHOP, Bundle())
        setFragmentResult(Constant.MANAGE_EDIT_SHOP, Bundle())
        findNavController().popBackStack()
    }
}