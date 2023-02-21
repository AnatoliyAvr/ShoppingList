package com.example.shoppinglist.ui.shopItem

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.ShopItem
import com.example.shoppinglist.R
import com.google.android.material.textfield.TextInputLayout

class ShopItemFragment(
    private val screenMode: String = MODE_UNKNOWN,
    private val shopItemId: Int = ShopItem.UNDEFINED_ID
) : Fragment() {

    private lateinit var viewModel: ShopItemViewModel

    private lateinit var tilName: TextInputLayout
    private lateinit var tilCount: TextInputLayout
    private lateinit var etName: EditText
    private lateinit var etCount: EditText
    private lateinit var btnSave: Button

//    private var screenMode = MODE_UNKNOWN
//    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shop_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseIntent()
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        initViews(view)
        addTextChangeListeners()
        launchRightMode()
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.apply {
            errorInputName.observe(viewLifecycleOwner) {
                tilName.error = if (it) {
                    getString(R.string.error_input_name)
                } else {
                    null
                }
            }
            errorInputCount.observe(viewLifecycleOwner) {
                tilCount.error = if (it) {
                    getString(R.string.error_input_count)
                } else {
                    null
                }
            }
            shouldCloseScreen.observe(viewLifecycleOwner) {
                activity?.onBackPressed()
            }
        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> lunchEditMode()
            MODE_ADD -> lunchAddMode()
        }
    }

    private fun addTextChangeListeners() {
        etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun lunchEditMode() {
        viewModel.apply {
            getShopItem(shopItemId)
            shopItem.observe(viewLifecycleOwner) {
                etName.setText(it.name)
                etCount.setText(it.count.toString())
            }
            btnSave.setOnClickListener {
                editShopItem(etName.text?.toString(), etCount.text?.toString())
            }
        }
    }

    private fun lunchAddMode() {
        viewModel.apply {
            btnSave.setOnClickListener {
                addShopItem(etName.text?.toString(), etCount.text?.toString())
            }
        }
    }

    private fun parseIntent() {
        if (screenMode != MODE_EDIT && screenMode != MODE_ADD) {
            throw RuntimeException("Param screen mode is absent")
        }
        if (screenMode == MODE_EDIT && shopItemId == ShopItem.UNDEFINED_ID) {
            throw RuntimeException("Param shop item id is absent")
        }
    }

    private fun initViews(view: View) {
        tilName = view.findViewById(R.id.til_name)
        tilCount = view.findViewById(R.id.til_count)
        etName = view.findViewById(R.id.et_name)
        etCount = view.findViewById(R.id.et_count)
        btnSave = view.findViewById(R.id.save_btn)
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"

        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem(): ShopItemFragment {
            return ShopItemFragment(MODE_ADD)
        }

        fun newInstanceEditItem(shopItemId: Int): ShopItemFragment {
            return ShopItemFragment(MODE_EDIT, shopItemId)
        }
    }
}