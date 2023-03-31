package ru.barsik.data.repository

import ru.barsik.data.datasource.local.CategoryLocalDataSource
import ru.barsik.data.datasource.remote.CategoryRemoteDataSource
import ru.barsik.domain.model.Category
import ru.barsik.domain.repository.CategoryRepository
import ru.barsik.domain.repository.ImageRepository

/**
 *
 * @author Mitryashkin
 * @since 28.03.2023
 */
class CategoryRepositoryImpl(
    private val imageRepository: ImageRepository,
    private val localDS: CategoryLocalDataSource,
    private val remoteDS: CategoryRemoteDataSource
) : CategoryRepository {
    private var _categoryList: List<Category>? = null
    override suspend fun getAllCategories(): List<Category> {
        return localDS.getCategories()
    }
}