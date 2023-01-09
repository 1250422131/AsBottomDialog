package com.imcys.asbottomdialog.bottomdialog.inface

import com.imcys.asbottomdialog.bottomdialog.AsDialog

sealed interface AsDialogListener{
    fun onClose(asDialog: AsDialog)
}