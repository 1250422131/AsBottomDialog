package com.imcys.asbottomdialog.inface

import com.imcys.asbottomdialog.AsDialog

sealed interface AsDialogListener{
    fun onClose(asDialog: AsDialog)
}