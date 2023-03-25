package ru.barsik.domain.model

data class Event(
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
)