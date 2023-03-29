package ru.barsik.domain.repository

import javax.swing.ImageIcon

interface ImageRepository {

    suspend fun getLocalImage(path: String) : ByteArray

    suspend fun getRemoteImage(path: String): ByteArray

}