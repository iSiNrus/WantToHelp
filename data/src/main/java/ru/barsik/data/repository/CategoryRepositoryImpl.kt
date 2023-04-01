package ru.barsik.data.repository

import android.util.Log
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import ru.barsik.data.datasource.local.CategoryLocalDataSource
import ru.barsik.data.datasource.remote.CategoryRemoteDataSource
import ru.barsik.data.mapper.toCategory
import ru.barsik.data.mapper.toCategoryEntity
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
        if (_categoryList == null) {
            try {
                withTimeout(5000) {
                    val categoryEntityList = remoteDS.getCategories()
                    _categoryList =
                        categoryEntityList.map { x -> x.toCategory(imageRepository.getRemoteImage(x.icon_path)) }
                }
            } catch (e: TimeoutCancellationException) {
                Log.e("TIMEOUT", "getAllEvents", e)
                val categoryList = localDS.getCategories()
                _categoryList =
                    categoryList.map { x -> x.toCategory(imageRepository.getLocalImage(x.icon_path)) }
            }
        }
        return _categoryList!!
    }
}