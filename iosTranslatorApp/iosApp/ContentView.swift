import SwiftUI
import Shared

struct ContentView: View {
    @State private var showContent = false
    
    var body: some View {
        ZStack {
            Color.background.ignoresSafeArea()
            TranslateScreen()
        }
    }
}
