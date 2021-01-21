package kg.nurik.cameraimage.di

import kg.nurik.cameraimage.BuildConfig.BASE_URL
import kg.nurik.cameraimage.data.interactor.UserInteractor
import kg.nurik.cameraimage.data.interactor.UserInteractorImpl
import kg.nurik.cameraimage.data.remote.RetrofitBuilder
import kg.nurik.cameraimage.data.repositories.UserRepository
import kg.nurik.cameraimage.data.repositories.UserRepositoryImpl
import kg.nurik.cameraimage.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
   viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    single { RetrofitBuilder.initRetrofit(BASE_URL) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<UserInteractor> { UserInteractorImpl(get()) }

}

val appModules = listOf(viewModelModule, repositoryModule)