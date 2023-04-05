package ru.barsik.wanttohelp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.barsik.data.datasource.EventDataSource
import ru.barsik.data.datasource.local.CategoryLocalDataSource
import ru.barsik.data.datasource.local.EventLocalDataSource
import ru.barsik.data.datasource.remote.CategoryRemoteDataSource
import ru.barsik.data.datasource.remote.EventRemoteDataSource
import ru.barsik.data.repository.CategoryRepositoryImpl
import ru.barsik.data.repository.EventRepositoryImpl
import ru.barsik.data.repository.ImageRepositoryImpl
import ru.barsik.domain.repository.CategoryRepository
import ru.barsik.domain.repository.EventRepository
import ru.barsik.domain.repository.ImageRepository

@Module
class DataModule(context: Context) {

    @Provides
    fun provideImageRepository(context: Context): ImageRepository {
        return ImageRepositoryImpl(context)
    }

    @Provides
    fun provideEventRepository(
        imageRepository: ImageRepository,
        localDataSource: EventLocalDataSource,
        remoteDataSource: EventRemoteDataSource
    ): EventRepository {
        return EventRepositoryImpl(imageRepository, localDataSource, remoteDataSource)
    }

    @Provides
    fun provideCategoryRepository(
        imageRepository: ImageRepository,
        remoteDataSource: CategoryRemoteDataSource,
        localDataSource: CategoryLocalDataSource
    ): CategoryRepository {
        return CategoryRepositoryImpl(imageRepository, localDataSource, remoteDataSource)
    }

    @Provides
    fun provideEventRemoteDataSource(): EventRemoteDataSource {
        return EventRemoteDataSource()
    }

    @Provides
    fun provideCategoryRemoteDataSource(): CategoryRemoteDataSource {
        return CategoryRemoteDataSource()
    }

    @Provides
    fun provideEventLocalDataSource(context: Context): EventLocalDataSource {
        return EventLocalDataSource(context)
    }

    @Provides
    fun provideCategoryLocalDataSource(context: Context): CategoryLocalDataSource {
        return CategoryLocalDataSource(context)
    }

}