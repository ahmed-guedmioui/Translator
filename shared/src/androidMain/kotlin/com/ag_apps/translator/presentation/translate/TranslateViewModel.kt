package com.ag_apps.translator.presentation.translate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

class TranslateViewModel : ViewModel() {

    private val viewModel by lazy {
       SharedTranslateViewModel(coroutineScope = viewModelScope)
    }

    val state = viewModel.state

    fun onAction(action: TranslateAction) {
        viewModel.onAction(action)
    }

}