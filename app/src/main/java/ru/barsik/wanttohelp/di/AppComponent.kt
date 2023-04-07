package ru.barsik.wanttohelp.di

import dagger.Component
import ru.barsik.wanttohelp.ui.auth.AuthFragment
import ru.barsik.wanttohelp.ui.categories.CategoriesFragment
import ru.barsik.wanttohelp.ui.event_info.EventInfoFragment
import ru.barsik.wanttohelp.ui.news.NewsFragment
import ru.barsik.wanttohelp.ui.news.filternews.FilterNewsFragment

@Component(modules = [DataModule::class, DomainModule::class, AppModule::class])
interface AppComponent {
    fun inject(newsFragment: NewsFragment)
    fun inject(filterNewsFragment: FilterNewsFragment)
    fun inject(authFragment: AuthFragment)
    fun inject(categoriesFragment: CategoriesFragment)
    fun inject(eventInfoFragment: EventInfoFragment)
}