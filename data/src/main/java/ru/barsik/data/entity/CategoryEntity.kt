package ru.barsik.data.entity

data class CategoryEntity(
    val id: Int,
    val title: String,
    val icon_path: String
) {
    constructor() : this(
        id = -1,
        title = "None",
        icon_path = "None"
    )
}
