package com.ag_apps.translator.presentation.translate

import com.ag_apps.translator.domain.core.history.HistoryDataSource
import com.ag_apps.translator.domain.core.util.Result
import com.ag_apps.translator.domain.core.util.toCommonStateFlow
import com.ag_apps.translator.domain.translate.TranslateException
import com.ag_apps.translator.domain.translate.TranslateUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SharedTranslateViewModel(coroutineScope: CoroutineScope?) : KoinComponent {

    private val translateUseCase: TranslateUseCase by inject()
    private val historyDataSource: HistoryDataSource by inject()

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private var translateJob: Job? = null

    private val _state = MutableStateFlow(TranslateState())
    val state = combine(
        _state, historyDataSource.getHistory()
    ) { state, history ->
        if (state.history != history) {
            state.copy(
                history = history.map { item ->
                    UiHistoryItem(
                        id = item.id ?: return@combine state,
                        fromText = item.fromText,
                        toText = item.toText,
                        fromLanguage = UiLanguage.byCode(item.fromLanguageCode),
                        toLanguage = UiLanguage.byCode(item.toLanguageCode)
                    )
                }
            )
        } else state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TranslateState())
        .toCommonStateFlow()

    fun onAction(action: TranslateAction) {
        when (action) {
            is TranslateAction.ChangeTranslationText -> {
                _state.update {
                    it.copy(
                        fromText = action.text + historyDataSource.test()
                    )
                }
            }

            is TranslateAction.ChooseFromLanguage -> {
                _state.update {
                    it.copy(
                        isChoosingFromLanguage = false,
                        fromLanguage = action.language
                    )
                }
            }

            is TranslateAction.ChooseToLanguage -> {
                val newState = _state.updateAndGet {
                    it.copy(
                        isChoosingToLanguage = false,
                        toLanguage = action.language
                    )
                }
                translate(newState)
            }

            TranslateAction.CloseTranslation -> {
                _state.update {
                    it.copy(
                        isTranslating = false,
                        fromText = "",
                        toText = null
                    )
                }
            }

            TranslateAction.EditTranslation -> {
                if (state.value.toText != null) {
                    _state.update {
                        it.copy(
                            toText = null,
                            isTranslating = false
                        )
                    }
                }
            }

            TranslateAction.OnErrorSeen -> {
                _state.update { it.copy(error = null) }
            }

            TranslateAction.OpenFromLanguageDropDown -> {
                _state.update {
                    it.copy(
                        isChoosingFromLanguage = true
                    )
                }
            }

            TranslateAction.OpenToLanguageDropDown -> {
                _state.update {
                    it.copy(
                        isChoosingToLanguage = true
                    )
                }
            }

            is TranslateAction.SelectHistoryItem -> {
                translateJob?.cancel()
                _state.update {
                    it.copy(
                        fromText = action.item.fromText,
                        toText = action.item.toText,
                        isTranslating = false,
                        fromLanguage = action.item.fromLanguage,
                        toLanguage = action.item.toLanguage
                    )
                }
            }

            TranslateAction.StopChoosingLanguage -> {
                _state.update {
                    it.copy(
                        isChoosingFromLanguage = false,
                        isChoosingToLanguage = false
                    )
                }
            }

            is TranslateAction.SubmitVoiceResult -> {
                _state.update {
                    it.copy(
                        fromText = action.result ?: it.fromText,
                        isTranslating = if (action.result != null) false else it.isTranslating,
                        toText = if (action.result != null) null else it.toText
                    )
                }
            }

            TranslateAction.SwapLanguages -> {
                _state.update {
                    it.copy(
                        fromLanguage = it.toLanguage,
                        toLanguage = it.fromLanguage,
                        fromText = it.toText ?: "",
                        toText = if (it.toText != null) it.fromText else null
                    )
                }
            }

            TranslateAction.Translate -> translate(state.value)

            else -> Unit
        }
    }

    private fun translate(state: TranslateState) {
        if (state.isTranslating || state.fromText.isBlank()) {
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isTranslating = true) }

            val result = translateUseCase(
                fromLanguage = state.fromLanguage.language,
                fromText = state.fromText,
                toLanguage = state.toLanguage.language
            )

            when (result) {
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isTranslating = false,
                            toText = result.data
                        )
                    }
                }

                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isTranslating = false,
                            error = (result.error as? TranslateException)?.error
                        )
                    }
                }
            }
        }
    }
}
