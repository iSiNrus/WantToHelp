package ru.barsik.wanttohelp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.barsik.domain.usecase.GetAllEventsUseCase
import ru.barsik.wanttohelp.App
import ru.barsik.wanttohelp.ui.news.NewsViewModel

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
}