package ru.barsik.wanttohelp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.barsik.domain.usecase.GetAllEventsUseCase
import ru.barsik.domain.usecase.categories.GetAllCategoriesUseCase
import ru.barsik.wanttohelp.App
import ru.barsik.wanttohelp.ui.auth.AuthViewModel
import ru.barsik.wanttohelp.ui.categories.CategoriesViewModel
import ru.barsik.wanttohelp.ui.news.NewsViewModel
import ru.barsik.wanttohelp.ui.news.filternews.FilterNewsViewModel

@Module
class AppModule(val application: App) {

    @Provides
    fun providesContext(): Context {
        return application.applicationContext
    }

    @Provides
    fun provideApp(): App {
        return application
    }

    @Provides
    fun provideNewsViewModule(getAllEventsUseCase: GetAllEventsUseCase): NewsViewModel {
        return NewsViewModel(getAllEventsUseCase, application)
    }

    @Provides
    fun provideFilterNewsViewModel(getAllCategoriesUseCase: GetAllCategoriesUseCase): FilterNewsViewModel {
        return FilterNewsViewModel(getAllCategoriesUseCase, application)
    }

    @Provides
    fun provideAuthViewModel(): AuthViewModel {
        return AuthViewModel(application)
    }

    @Provides
    fun provideCategoriesViewModel(getAllCategoriesUseCase: GetAllCategoriesUseCase): CategoriesViewModel {
        return CategoriesViewModel(getAllCategoriesUseCase, application)
    }
}