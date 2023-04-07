package ru.barsik.wanttohelp.di

import dagger.Component
import ru.barsik.wanttohelp.ui.news.NewsFragment
import ru.barsik.wanttohelp.ui.news.filternews.FilterNewsFragment

@Component(modules = [DataModule::class, DomainModule::class, AppModule::class])
interface AppComponent {
    fun inject(newsFragment: NewsFragment)
    fun inject(filterNewsFragment: FilterNewsFragment)
}