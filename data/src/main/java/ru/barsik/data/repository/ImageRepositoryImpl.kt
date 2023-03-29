package ru.barsik.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import ru.barsik.domain.repository.ImageRepository
import java.io.ByteArrayOutputStream

class ImageRepositoryImpl(private val ctx: Context) : ImageRepository {
    override suspend fun getLocalImage(path: String): ByteArray {
        val stream = ByteArrayOutputStream()
        BitmapFactory.decodeStream(ctx.resources.assets.open(path))
            .compress(Bitmap.CompressFormat.JPEG, 90, stream)
        return stream.toByteArray()
    }

    override suspend fun getRemoteImage(path: String): ByteArray {
        val picFirebase = Firebase.storage
        val ref = picFirebase.getReference(path)
        return ref.getBytes(Long.MAX_VALUE).await()
    }

}