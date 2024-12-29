//
//  TranslateViewModel.swift
//  iosTranslatorApp
//
//  Created by ahmed on 29/12/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

extension TranslateScreen {
    @MainActor class TranslateViewModel: ObservableObject {
        
        private let viewModel: SharedTranslateViewModel
        
        @Published var state: TranslateState = TranslateState(
            fromText: "",
            toText: nil,
            isTranslating: false,
            fromLanguage: UiLanguage(imageName: "english", language: .english),
            toLanguage: UiLanguage(imageName: "german", language: .german),
            isChoosingFromLanguage: false,
            isChoosingToLanguage: false,
            error: nil,
            history: []
        )
        private var handle: DisposableHandle?
        
        init() {
            self.viewModel = SharedTranslateViewModel(coroutineScope: nil)
        }
        
        func onAction(action: TranslateAction) {
            self.viewModel.onAction(action: action)
        }

        func startObserving() {
            handle = viewModel.state.subscribe(onCollect: { state in
               if let state = state {
                   self.state = state
               }
           })
        }
                
        func dispose() {
            handle?.dispose()
        }
    }
}
