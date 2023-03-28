package ru.barsik.data.datasource

import ru.barsik.domain.model.Category

/**
 *
 * @author Mitryashkin
 * @since 28.03.2023
 */
interface CategoryDataSource {

    suspend fun getCategories() : List<Category>

}