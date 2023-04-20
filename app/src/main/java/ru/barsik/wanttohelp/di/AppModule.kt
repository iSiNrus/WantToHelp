package ru.barsik.wanttohelp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.barsik.domain.usecase.GetAllEventsUseCase
import ru.barsik.domain.usecase.GetEventByIdUseCase
import ru.barsik.domain.usecase.SearchEventByNKOUseCase
import ru.barsik.domain.usecase.SearchEventByTitleUseCase
import ru.barsik.domain.usecase.categories.GetAllCategoriesUseCase
import ru.barsik.wanttohelp.App
import ru.barsik.wanttohelp.ui.auth.AuthViewModel
import ru.barsik.wanttohelp.ui.categories.CategoriesViewModel
import ru.barsik.wanttohelp.ui.event_info.EventInfoViewModel
import ru.barsik.wanttohelp.ui.news.NewsViewModel
import ru.barsik.wanttohelp.ui.news.filternews.FilterNewsViewModel
import ru.barsik.wanttohelp.ui.search.SearchViewModel

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

    @ViewModelScope
    @Provides
    fun provideNewsViewModule(getAllEventsUseCase: GetAllEventsUseCase): NewsViewModel {
        return NewsViewModel(getAllEventsUseCase, application)
    }

    @ViewModelScope
    @Provides
    fun provideFilterNewsViewModel(getAllCategoriesUseCase: GetAllCategoriesUseCase): FilterNewsViewModel {
        return FilterNewsViewModel(getAllCategoriesUseCase, application)
    }

    @ViewModelScope
    @Provides
    fun provideAuthViewModel(): AuthViewModel {
        return AuthViewModel(application)
    }

    @ViewModelScope
    @Provides
    fun provideCategoriesViewModel(getAllCategoriesUseCase: GetAllCategoriesUseCase): CategoriesViewModel {
        return CategoriesViewModel(getAllCategoriesUseCase, application)
    }

    @ViewModelScope
    @Provides
    fun provideEventInfoViewModel(getEventByIdUseCase: GetEventByIdUseCase): EventInfoViewModel {
        return EventInfoViewModel(getEventByIdUseCase, application)
    }

    @ViewModelScope
    @Provides
    fun provideSearchViewModel(
        searchNkoUseCase: SearchEventByNKOUseCase,
        searchEventUseCase: SearchEventByTitleUseCase
    ): SearchViewModel {
        return SearchViewModel(searchNkoUseCase, searchEventUseCase, application)
    }
}