//
//  TranslateTextField.swift
//  iosTranslatorApp
//
//  Created by ahmed on 29/12/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared
import UniformTypeIdentifiers

struct TranslateTextField: View {
    @Binding var fromText: String
    let toText: String?
    let isTranslating: Bool
    let fromLanguage: UiLanguage
    let toLanguage: UiLanguage
    let onTranslateEvent: (TranslateAction) -> Void
    
    var body: some View {
        if toText == nil || isTranslating {
            IdleTextField(fromText: $fromText, isTranslating: isTranslating, onTranslateEvent: onTranslateEvent)
                .cornerRadius(15)
                .animation(.easeInOut, value: isTranslating)
                .shadow(radius: 4)
        } else {
            TranslatedTextField(
                fromText: fromText,
                toText: toText ?? "",
                fromLanguage: fromLanguage,
                toLanguage: toLanguage,
                onTranslateEvent: onTranslateEvent
            )
            .padding()
            .gradientSurface()
            .cornerRadius(15)
            .animation(.easeInOut, value: isTranslating)
            .shadow(radius: 4)
            .onTapGesture {
                onTranslateEvent(TranslateAction.EditTranslation())
            }
        }
    }
}

struct TranslateTextField_Previews: PreviewProvider {
    static var previews: some View {
        TranslateTextField(
            fromText: Binding(
                get: { "test" },
                set: { value in }
            ),
            toText: "Test",
            isTranslating: false,
            fromLanguage: UiLanguage(imageName: "english", language: .english),
            toLanguage: UiLanguage(imageName: "german", language: .german),
            onTranslateEvent: { event in }
        )
    }
}

private extension TranslateTextField {
    
    struct IdleTextField: View {
        @Binding var fromText: String
        let isTranslating: Bool
        let onTranslateEvent: (TranslateAction) -> Void
        
        var body: some View {
            TextEditor(text: $fromText)
                .frame(
                    maxWidth: .infinity,
                    minHeight: 200,
                    alignment: .topLeading
                )
                .padding()
                .foregroundColor(Color.onSurface)
                .overlay(alignment: .bottomTrailing) {
                    ProgressButton(
                        text: "Translate",
                        isLoading: isTranslating,
                        onClick: {
                            onTranslateEvent(TranslateAction.Translate())
                        }
                    )
                    .padding(.trailing)
                    .padding(.bottom)
                }
                .onAppear {
                    UITextView.appearance().backgroundColor = .clear
                }
        }
    }
    
    struct TranslatedTextField: View {
        let fromText: String
        let toText: String
        let fromLanguage: UiLanguage
        let toLanguage: UiLanguage
        let onTranslateEvent: (TranslateAction) -> Void
        
        var body: some View {
            VStack(alignment: .leading) {
                LanguageDisplay(language: fromLanguage)
                Text(fromText)
                    .foregroundColor(.onSurface)
                HStack {
                    Spacer()
                    Button(action: {
                        UIPasteboard.general.setValue(
                            fromText,
                            forPasteboardType: UTType.plainText.identifier
                        )
                    }) {
                        Image(uiImage: UIImage(named: "copy")!)
                            .renderingMode(.template)
                            .foregroundColor(.lightBlue)
                    }
                    Button(action: {
                        onTranslateEvent(TranslateAction.CloseTranslation())
                    }) {
                        Image(systemName: "xmark")
                            .foregroundColor(.lightBlue)
                    }
                }
                Divider()
                    .padding()
                LanguageDisplay(language: toLanguage)
                    .padding(.bottom)
                Text(toText)
                    .foregroundColor(.onSurface)
                
                HStack {
                    Spacer()
                    Button(action: {
                        UIPasteboard.general.setValue(
                            toText,
                            forPasteboardType: UTType.plainText.identifier
                        )
                    }) {
                        Image(uiImage: UIImage(named: "copy")!)
                            .renderingMode(.template)
                            .foregroundColor(.lightBlue)
                    }
                    Button(action: {
                        
                    }) {
                        Image(systemName: "speaker.wave.2")
                            .foregroundColor(.lightBlue)
                    }
                }
            }
        }
    }
}
