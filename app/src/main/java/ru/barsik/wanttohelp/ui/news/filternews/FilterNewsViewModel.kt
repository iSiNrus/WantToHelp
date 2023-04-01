package ru.barsik.wanttohelp.ui.news.filternews

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.barsik.data.datasource.local.CategoryLocalDataSource
import ru.barsik.data.datasource.remote.CategoryRemoteDataSource
import ru.barsik.data.repository.CategoryRepositoryImpl
import ru.barsik.data.repository.ImageRepositoryImpl
import ru.barsik.domain.model.Category
import ru.barsik.domain.usecase.categories.GetAllCategoriesUseCase

class FilterNewsViewModel(application: Application) : AndroidViewModel(application) {

    private val categoriesListLiveData = MutableLiveData<List<Category>>()
    private val resultList = ArrayList<Category>()

    private val getAllCategoriesUseCase = GetAllCategoriesUseCase(
        CategoryRepositoryImpl(
            ImageRepositoryImpl(application),
            CategoryLocalDataSource(application),
            CategoryRemoteDataSource()
        )
    )

    fun getCategories() {
        viewModelScope.launch {
            categoriesListLiveData.postValue(getAllCategoriesUseCase.execute())
        }
    }

    fun getCategoriesListLD() = categoriesListLiveData
    fun addCategoryToResult(category: Category) {
        resultList.add(category)
    }

    fun removeCategoryFromResult(category: Category) {
        resultList.remove(category)
    }
}