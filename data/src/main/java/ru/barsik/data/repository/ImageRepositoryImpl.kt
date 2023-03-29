package ru.barsik.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import ru.barsik.domain.repository.ImageRepository
import java.io.ByteArrayOutputStream

class ImageRepositoryImpl(private val ctx: Context) : ImageRepository {
    override suspend fun getImage(path: String): ByteArray {
        val stream = ByteArrayOutputStream()
        BitmapFactory.decodeStream(ctx.resources.assets.open(path))
            .compress(Bitmap.CompressFormat.JPEG, 90, stream)
        return stream.toByteArray()
    }

}