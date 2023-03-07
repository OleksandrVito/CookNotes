package ua.vitolex.cooknotes.di

import android.app.Application
import android.content.Intent
import android.net.Uri
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteApp: Application(){
    //це все для загрузки фоток
    init {
        instance = this
    }
    companion object {
        private var instance: NoteApp? = null

        fun getUriPermission(uri: Uri){
            instance!!.applicationContext.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
    }
}