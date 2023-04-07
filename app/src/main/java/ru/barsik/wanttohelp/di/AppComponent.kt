package ru.barsik.wanttohelp.di

import dagger.Component
import ru.barsik.wanttohelp.ui.news.NewsFragment

@Component(modules = [DataModule::class, DomainModule::class, AppModule::class])
interface AppComponent {
    fun inject(newsFragment: NewsFragment)
}