package com.dominic.shoppinglist.ui.manage

import androidx.lifecycle.ViewModel
import com.dominic.shoppinglist.data.model.Shop
import com.dominic.shoppinglist.data.repo.ShopsRepo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class BaseManageViewModel(
    protected val repo: ShopsRepo = ShopsRepo.getInstance()
) : ViewModel() {

    protected val _finish = MutableSharedFlow<Unit>()
    val finish: SharedFlow<Unit> = _finish

    protected val _error = MutableSharedFlow<String>()
    val error: SharedFlow<String> = _error

    abstract fun submit(shop: Shop)
}