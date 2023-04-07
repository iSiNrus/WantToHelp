package ru.barsik.wanttohelp.ui.categories

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
import ru.barsik.domain.repository.CategoryRepository
import ru.barsik.domain.usecase.categories.GetAllCategoriesUseCase
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val categoriesLiveData = MutableLiveData<List<Category>>()

    fun getCategories() {
        viewModelScope.launch {
            categoriesLiveData.postValue(getAllCategoriesUseCase.execute())
        }
    }

    fun getCategoriesLD() = categoriesLiveData
}