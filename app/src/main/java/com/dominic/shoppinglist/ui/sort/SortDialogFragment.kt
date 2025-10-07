package com.dominic.shoppinglist.ui.sort

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.dominic.shoppinglist.R
import com.dominic.shoppinglist.data.enums.SortBy
import com.dominic.shoppinglist.databinding.SortPopupBinding

class SortDialogFragment(
    private val currentSort: SortBy,
    private val onSortClick: (SortBy) -> Unit
) : DialogFragment() {

    private lateinit var binding: SortPopupBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = SortPopupBinding.inflate(layoutInflater)
        setRadio(currentSort)

        val dialog = Dialog(requireContext())
        dialog.setContentView(binding.root)

        binding.mbDone.setOnClickListener {
            val sortType = when (binding.rgSort.checkedRadioButtonId) {
                R.id.rbAZ -> SortBy.AZ
                else -> SortBy.ZA
            }
            onSortClick(sortType)
            dismiss()
        }
        return dialog
    }

    private fun setRadio(currentSort: SortBy) {
        when (currentSort) {
            SortBy.AZ -> binding.rbAZ.isChecked = true
            SortBy.ZA -> binding.rbZA.isChecked = true
        }
    }
}
