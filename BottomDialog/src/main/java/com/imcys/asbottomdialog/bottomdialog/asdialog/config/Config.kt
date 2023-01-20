package com.imcys.asbottomdialog.bottomdialog.asdialog.config

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import com.imcys.asbottomdialog.bottomdialog.AsDialog

class Config {

    var title: String = "标题"
    var content: String? = null
    var imageDrawable: Drawable? = null
    var imageBitmap: Bitmap? = null
    var imageIcon: Icon? = null
    var cancelable: Boolean = true
    var positiveButtonText :String? = null
    var negativeButtonText :String? = null
    var neutralButtonText :String? = null
    var neutralButton :((AsDialog) -> Unit)? = null
    var positiveButton:((AsDialog) -> Unit)? = null
    var negativeButton: ((AsDialog) -> Unit)? = null


}