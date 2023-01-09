package com.imcys.asbottomdialog.bottomdialog.inface

import com.imcys.asbottomdialog.AsDialog

sealed interface AsDialogListener{
    fun onClose(asDialog: AsDialog)
}