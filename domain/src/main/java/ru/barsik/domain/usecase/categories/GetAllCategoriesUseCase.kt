package ru.barsik.domain.usecase.categories

import ru.barsik.domain.model.Category
import ru.barsik.domain.repository.CategoryRepository

class GetAllCategoriesUseCase(private val categoryRepository: CategoryRepository) {

    suspend fun execute(): List<Category> {
        return categoryRepository.getAllCategories()
    }

}