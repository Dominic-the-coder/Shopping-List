package com.dominic.shoppinglist.ui.manage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.dominic.shoppinglist.R
import com.dominic.shoppinglist.data.model.Shop
import com.dominic.shoppinglist.ui.general.ShopDetailsFragmentArgs

class EditShopFragment : BaseManageFragment() {

    override val viewModel: EditShopViewModel by viewModels()
    private val args: ShopDetailsFragmentArgs by navArgs()
    private lateinit var shop: Shop

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shop = viewModel.getShop(args.shopId)
        binding.run {
            // Populate EditText fields
            setText(shop)
            mbSubmit.setOnClickListener {
                viewModel.submit(
                    shop.copy(
                        name = etName.text.toString(),
                        desc = etDesc.text.toString(),
                        quantity = etQuantity.text.toString().toIntOrNull() ?: 0
                    )
                )
            }
        }
    }

    private fun setText(shop: Shop?) {
        binding.run {
            mbSubmit.text = getString(R.string.update)
            mtManage.title = getString(R.string.update_list)
            etName.setText(shop?.name)
            etDesc.setText(shop?.desc)
            etQuantity.setText(shop?.quantity?.toString() ?: "0")
        }
    }
}