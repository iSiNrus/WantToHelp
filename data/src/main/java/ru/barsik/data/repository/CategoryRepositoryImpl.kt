package ru.barsik.data.repository

import ru.barsik.data.datasource.local.CategoryLocalDataSource
import ru.barsik.data.datasource.remote.CategoryRemoteDataSource
import ru.barsik.domain.model.Category
import ru.barsik.domain.repository.CategoryRepository

/**
 *
 * @author Mitryashkin
 * @since 28.03.2023
 */
class CategoryRepositoryImpl(
    private val localDS: CategoryLocalDataSource,
    private val remoteDS: CategoryRemoteDataSource
) : CategoryRepository {
    override suspend fun getAllCategories(): List<Category> {
        return localDS.getCategories()
    }
}