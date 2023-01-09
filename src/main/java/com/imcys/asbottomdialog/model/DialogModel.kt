package com.imcys.asbottomdialog.model

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.imcys.asbottomdialog.AsDialog
import com.imcys.asbottomdialog.inface.AsDialogListener
import com.imcys.asbottomdialog.databinding.AsdialogBottomsheetBinding

class DialogModel {

    var title: String = "标题"
    var content: String? = null
    var imageDrawable: Drawable? = null
    var imageBitmap:Bitmap? = null
    var imageIcon:Icon? = null

    var positiveButtonText :String? = null
    var negativeButtonText :String? = null

    var positiveButton:((AsDialog) -> Unit)? = null
    var negativeButton: ((AsDialog) -> Unit)? = null
    var asDialogListener: AsDialogListener? = null
    var cancelable: Boolean = true
    lateinit var bottomSheetDialog: BottomSheetDialog
    lateinit var asDialogBottomsheetBinding:AsdialogBottomsheetBinding
}