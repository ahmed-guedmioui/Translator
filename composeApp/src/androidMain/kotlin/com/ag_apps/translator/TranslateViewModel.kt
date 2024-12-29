package com.ag_apps.translator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag_apps.translator.presentation.translate.SharedTranslateViewModel
import com.ag_apps.translator.presentation.translate.TranslateAction

class TranslateViewModel : ViewModel() {

    private val viewModel by lazy {
       SharedTranslateViewModel(coroutineScope = viewModelScope)
    }

    val state = viewModel.state

    fun onAction(action: TranslateAction) {
        viewModel.onAction(action)
    }

}