package ru.barsik.domain.repository

import javax.swing.ImageIcon

interface ImageRepository {

    suspend fun getImage(path: String) : ByteArray

}