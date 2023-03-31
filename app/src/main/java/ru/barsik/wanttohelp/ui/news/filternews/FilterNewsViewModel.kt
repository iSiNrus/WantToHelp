package ru.barsik.wanttohelp.ui.news.filternews

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.barsik.data.datasource.local.CategoryLocalDataSource
import ru.barsik.data.datasource.remote.CategoryRemoteDataSource
import ru.barsik.data.repository.CategoryRepositoryImpl
import ru.barsik.data.repository.ImageRepositoryImpl
import ru.barsik.domain.usecase.categories.GetAllCategoriesUseCase

class FilterNewsViewModel(application: Application) : AndroidViewModel(application) {

    private val getAllCategoriesUseCase = GetAllCategoriesUseCase(
        CategoryRepositoryImpl(
            ImageRepositoryImpl(application),
            CategoryLocalDataSource(application),
            CategoryRemoteDataSource()
        )
    )
}