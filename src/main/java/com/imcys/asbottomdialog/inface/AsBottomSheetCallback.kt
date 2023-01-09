package com.imcys.asbottomdialog.inface

import android.view.View

abstract class AsBottomSheetCallback {

    abstract fun onStateChanged(bottomSheet: View, newState: Int): Boolean

    abstract fun onSlide(bottomSheet: View, slideOffset: Float): Boolean

}