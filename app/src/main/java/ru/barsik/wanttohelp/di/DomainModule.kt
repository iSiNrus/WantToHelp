package ru.barsik.wanttohelp.di

import dagger.Module
import dagger.Provides
import ru.barsik.domain.repository.CategoryRepository
import ru.barsik.domain.repository.EventRepository
import ru.barsik.domain.usecase.GetAllEventsUseCase
import ru.barsik.domain.usecase.GetEventByIdUseCase
import ru.barsik.domain.usecase.SearchEventByNKOUseCase
import ru.barsik.domain.usecase.SearchEventByTitleUseCase
import ru.barsik.domain.usecase.categories.GetAllCategoriesUseCase

@Module
class DomainModule {

    @Provides
    fun provideGetAllCategoriesUseCase(categoryRepository: CategoryRepository): GetAllCategoriesUseCase {
        return GetAllCategoriesUseCase(categoryRepository)
    }

    @Provides
    fun provideGetEventByIdUseCase(eventRepository: EventRepository): GetEventByIdUseCase {
        return GetEventByIdUseCase(eventRepository)
    }

    @Provides
    fun providesGetAllEventsUseCase(eventRepository: EventRepository): GetAllEventsUseCase {
        return GetAllEventsUseCase(eventRepository)
    }

    @Provides
    fun provideSearchEventByTitleUseCase(eventRepository: EventRepository): SearchEventByTitleUseCase {
        return SearchEventByTitleUseCase(eventRepository)
    }

    @Provides
    fun provideSearchEventByNKOUseCase(eventRepository: EventRepository): SearchEventByNKOUseCase {
        return SearchEventByNKOUseCase(eventRepository)
    }

}