package ru.barsik.domain.repository

import ru.barsik.domain.model.Category

interface CategoryRepository {

    suspend fun getAllCategories() : List<Category>

}