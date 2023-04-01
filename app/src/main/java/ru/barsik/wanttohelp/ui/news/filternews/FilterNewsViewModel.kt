package ru.barsik.wanttohelp.ui.news.filternews

import android.app.Application
import android.util.Log
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

    private val TAG = "FilterNewsViewModel"
    private val categoriesListLiveData = MutableLiveData<List<Category>>()
    private var allCategories: List<Category>? = null
    private lateinit var resultList : ArrayList<Int>

    private val getAllCategoriesUseCase = GetAllCategoriesUseCase(
        CategoryRepositoryImpl(
            ImageRepositoryImpl(application),
            CategoryLocalDataSource(application),
            CategoryRemoteDataSource()
        )
    )

    fun getResultList() = resultList

    fun getCategories() {
        if(allCategories==null) {
            Log.d(TAG, "getCategories: GET NEW CATEGORIES view model ${this.hashCode()}")
            viewModelScope.launch {
                allCategories = getAllCategoriesUseCase.execute()
                resultList = allCategories!!.map { it.id } as ArrayList<Int>
                categoriesListLiveData.postValue(allCategories!!)
            }
        } else {
            resultList = allCategories!!.map { it.id } as ArrayList<Int>
        }
    }

    fun getCategoriesListLD() = categoriesListLiveData
    fun addCategoryToResult(categoryId: Int) {
        resultList.add(categoryId)
    }

    fun removeCategoryFromResult(categoryId: Int) {
        resultList.remove(categoryId)
    }
}