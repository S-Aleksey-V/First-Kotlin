package me.tolkstudio.firstkotlin

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import me.tolkstudio.firstkotlin.model.Repository
import me.tolkstudio.firstkotlin.providers.FireStoreProvider
import me.tolkstudio.firstkotlin.providers.RemoteDataProvider
import me.tolkstudio.firstkotlin.viewmodel.MainViewModel
import me.tolkstudio.firstkotlin.viewmodel.NoteViewModel
import me.tolkstudio.firstkotlin.viewmodel.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module


val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FireStoreProvider(get(), get()) } bind RemoteDataProvider::class
    single { Repository(get()) }
}

val splashModule = module {
    viewModel { SplashViewModel(get()) }
}
val mainModule = module {
    viewModel { MainViewModel(get()) }
}
val noteModule = module {
    viewModel { NoteViewModel(get()) }
}