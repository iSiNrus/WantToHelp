package ru.barsik.data.datasource

import ru.barsik.data.entity.CategoryEntity

/**
 *
 * @author Mitryashkin
 * @since 28.03.2023
 */
interface CategoryDataSource {

    suspend fun getCategories() : List<CategoryEntity>

}