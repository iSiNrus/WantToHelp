package ru.barsik.wanttohelp.ui.profile

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    var avatarBitmap: Bitmap? = null

}