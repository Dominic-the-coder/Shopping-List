package com.dominic.shoppinglist.ui.detail

import android.app.Dialog
import android.graphics.Color
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dominic.shoppinglist.data.model.Shop
import com.dominic.shoppinglist.databinding.FragmentShopDetailsBinding
import com.google.android.material.button.MaterialButton
import com.dominic.shoppinglist.R
import com.dominic.shoppinglist.data.enums.Status
import com.dominic.shoppinglist.data.utils.Constant

class ShopDetailsFragment : Fragment() {

    private val viewModel: ShopDetailsViewModel by viewModels()
    private lateinit var binding: FragmentShopDetailsBinding
    private val args: ShopDetailsFragmentArgs by navArgs()
    private lateinit var shop: Shop

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shop = viewModel.getShop(args.shopId)
        setData(shop)
        setFragmentResultListener(Constant.MANAGE_EDIT_SHOP, { _, _ -> getShop() })
    }

    fun setData(shop: Shop?) {
        binding.run {
            mtDetails.setNavigationOnClickListener { findNavController().popBackStack() }
            tvName.text = shop?.name
            tvDesc.text = shop?.desc
            tvQuantity.text = shop?.quantity?.toString() ?: "0"

        }
        setOnClickListeners()
    }

    fun setOnClickListeners() {
        binding.run {
            mbDone.text = if (shop.status == Status.INCART) getString(R.string.undone)
            else getString(R.string.done)
            mbDone.setOnClickListener {
                val dialog = createCompletedDialog()
                dialog.show()
            }
            mbConfirm.setOnClickListener {
                val dialog = createDeleteDialog(args.shopId)
                dialog.show()
            }
            mbUpdate.setOnClickListener {
                val action = ShopDetailsFragmentDirections.actionShopDetailsFragmentToEditShopFragment(args.shopId)
                findNavController().navigate(action)
            }
        }
    }

    fun getShop() {
        val newShop = viewModel.getShop(args.shopId)
        shop = newShop
        setData(newShop)
    }

    fun setStatus() {
        viewModel.changeStatus(shop)
        getShop()
        setFragmentResult("manage_shop", Bundle())
    }

    fun createCompletedDialog(): Dialog {
        return Dialog(requireContext()).apply {
            setContentView(R.layout.confirmation_dialog)
            window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            findViewById<TextView>(R.id.tvConfirm).text = if (shop.status == Status.NOTINCART) {
                getString(R.string.to_complete_question)
            } else {
                getString(R.string.to_new_question)
            }
            findViewById<MaterialButton>(R.id.mbCancel).apply {
                text = getString(R.string.no)
                setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.teal))
                setOnClickListener { dismiss() }
            }
            findViewById<MaterialButton>(R.id.mbConfirm).apply {
                text = getString(R.string.yes)
                setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
                setOnClickListener { setStatus(); dismiss() }
            }
        }
    }

    fun createDeleteDialog(wordId: Int): Dialog {
        return Dialog(requireContext()).apply {
            setContentView(R.layout.confirmation_dialog)
            window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            findViewById<MaterialButton>(R.id.mbCancel).setOnClickListener { dismiss() }
            findViewById<MaterialButton>(R.id.mbConfirm).setOnClickListener {
                viewModel.deleteShop(wordId)
                setFragmentResult("manage_shop", Bundle())
                findNavController().popBackStack()
                dismiss()
            }
        }
    }
}