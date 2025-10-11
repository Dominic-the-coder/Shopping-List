package com.dominic.shoppinglist.ui.manage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.dominic.shoppinglist.R
import com.dominic.shoppinglist.data.model.Shop

class AddShopFragment : BaseManageFragment() {
    override val viewModel: AddShopViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            mbSubmit.text = getString(R.string.add)
            mbSubmit.setOnClickListener {
                viewModel.submit(
                    Shop(
                        name = etName.text.toString(),
                        notes = etNote.text.toString(),
                        category = etCategory.text.toString(),
                        quantity = etQuantity.text.toString().toIntOrNull() ?: 0
                    )
                )
            }
        }
    }
}