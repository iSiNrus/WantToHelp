package ru.barsik.data.entity


data class EventEntity(
    val id: Int,
    val title: String,
    val date_start: Long,
    val date_finish: Long,
    val organization: String,
    val location: String,
    val contact_numbers: List<String>,
    val email: String,
    val description: String,
    val org_site: String,
    val title_img_path: String,
    val categories: List<Int>
){
    constructor() : this(
        -1,
        "None",
        0,
        0,
        "None",
        "None",
        emptyList<String>(),
        "None",
        "None",
        "None",
        "None",
        listOf(1)
    )
}