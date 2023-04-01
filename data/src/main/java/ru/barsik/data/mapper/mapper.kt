package ru.barsik.data.mapper

import ru.barsik.data.entity.CategoryEntity
import ru.barsik.data.entity.EventEntity
import ru.barsik.domain.model.Category
import ru.barsik.domain.model.Event

fun Event.toEventEntity(): EventEntity {
    return EventEntity(
        id = id,
        title = title,
        date_start = date_start,
        date_finish = date_finish,
        organization = organization,
        location = location,
        contact_numbers = contact_numbers,
        email = email,
        description = description,
        org_site = org_site,
        title_img_path = title_img_path,
        categories = categories
    )
}

fun EventEntity.toEvent(imageByteArray: ByteArray): Event {
    return Event(
        id = id,
        title = title,
        date_start = date_start,
        date_finish = date_finish,
        organization = organization,
        location = location,
        contact_numbers = contact_numbers,
        email = email,
        description = description,
        org_site = org_site,
        title_img_path = title_img_path,
        categories = categories,
        imageByteArray = imageByteArray
    )
}

fun Category.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(id, title, icon_path)
}

fun CategoryEntity.toCategory(imageByteArray: ByteArray): Category {
    return Category(id, title, icon_path, imageByteArray)
}