package com.imcys.asbottomdialog.bottomdialog

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewParent
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.imcys.asbottomdialog.bottomdialog.asdialog.config.Config
import com.imcys.asbottomdialog.bottomdialog.base.BaseDialog
import com.imcys.asbottomdialog.bottomdialog.databinding.AsdialogBottomsheetBinding
import com.imcys.asbottomdialog.bottomdialog.inface.AsBottomSheetCallback
import com.imcys.asbottomdialog.bottomdialog.inface.AsDialogListener
import com.imcys.asbottomdialog.bottomdialog.model.DialogModel
import com.imcys.asbottomdialog.bottomdialog.utils.FastBlurUtils
import kotlin.math.abs
import kotlin.math.roundToInt


/**
 * 对话框构建类
 */
object AsDialog {

    const val DEFAULT = 0
    const val GAUSSIAN_BLUR = 1

    @SuppressLint("StaticFieldLeak")
    lateinit var baseDialog: BaseDialog
    private val dialogModel: DialogModel = DialogModel()

    // 默认不执行任何
    var asBottomSheetCallback: AsBottomSheetCallback = object : AsBottomSheetCallback() {
        /**
         * 底部对话框监听
         * @param bottomSheet View
         * @param newState Int
         * @return Boolean false为不移除已有的监听代码 true则为移除
         */
        override fun onStateChanged(bottomSheet: View, newState: Int): Boolean {
            return false
        }


        override fun onSlide(bottomSheet: View, slideOffset: Float): Boolean {
            return false

        }

    }


    /**
     * 初始化弹窗对象
     * @param activity Activity
     * @return AsDialog
     */
    fun init(activity: Activity): AsDialog {
        baseDialog = BaseDialog()
        baseDialog.context = activity
        return this
    }

    /**
     * 初始化弹窗对象
     * @param fragment Fragment
     * @return AsDialog
     */
    fun init(fragment: Fragment): AsDialog {
        baseDialog = BaseDialog()
        baseDialog.context = fragment.requireContext()
        return this
    }


    /**
     * 初始化弹窗对象
     * @param context Context
     * @return AsDialog
     */
    fun init(context: Context): AsDialog {
        baseDialog = BaseDialog()
        baseDialog.context = context
        return this
    }


    /**
     * 设置标题
     * @param title String
     */
    fun setTitle(title: String): AsDialog {
        dialogModel.title = title
        return this
    }

    /**
     * 设置内容
     * @param content String
     */
    fun setContent(content: String): AsDialog {
        dialogModel.content = content
        return this

    }

    /**
     * 设置图片
     * @param imageDrawable Drawable
     */
    fun setImageUrl(imageDrawable: Drawable): AsDialog {
        dialogModel.imageDrawable = imageDrawable
        return this

    }

    fun setImageIcon(imaIcon: Icon): AsDialog {
        dialogModel.imageIcon = imaIcon
        return this

    }

    fun setImageBitmap(imageBitmap: Bitmap): AsDialog {
        dialogModel.imageBitmap = imageBitmap
        return this

    }


    /**
     * 获取底部对话框
     * @return BottomSheetDialog
     */
    fun bottomSheetDialog(): BottomSheetDialog {
        return dialogModel.bottomSheetDialog
    }

    /**
     * 关闭对话框
     */
    fun cancel() {
        dialogModel.bottomSheetDialog.cancel()
    }

    /**
     * 设置突出按钮
     * @param title String
     * @param asDialog Function1<AsDialog, Unit>
     */
    fun setPositiveButton(title: String, asDialog: (AsDialog) -> Unit): AsDialog {
        dialogModel.positiveButton = asDialog
        dialogModel.positiveButtonText = title
        return this
    }

    /**
     * 设置次要按钮
     * @param title String
     * @param asDialog Function1<AsDialog, Unit>
     */
    fun setNegativeButton(title: String, asDialog: (AsDialog) -> Unit): AsDialog {
        dialogModel.negativeButton = asDialog
        dialogModel.negativeButtonText = title
        return this
    }

    /**
     * 设置中立按钮
     * @param title String
     * @param asDialog Function1<AsDialog, Unit>
     */
    fun setNeutralButton(title: String, asDialog: (AsDialog) -> Unit): AsDialog {
        dialogModel.neutralButton = asDialog
        dialogModel.neutralButtonText = title
        return this
    }

    /**
     * 设置事件监听器
     * @param asDialogListener AsDialogListener
     */
    fun addListener(asDialogListener: AsDialogListener): AsDialog {
        dialogModel.asDialogListener = asDialogListener
        return this
    }

    /**
     * 设置底部对话框监听
     * @param asBottomSheetCallback AsBottomSheetCallback
     */
    fun setBottomSheetCallback(asBottomSheetCallback: AsBottomSheetCallback): AsDialog {
        AsDialog.asBottomSheetCallback = asBottomSheetCallback
        return this
    }

    /**
     * 设置对话框可取消
     * @param cancelable Boolean
     * @return AsDialog
     */
    fun setCancelable(cancelable: Boolean): AsDialog {
        dialogModel.cancelable = cancelable
        return this
    }

    fun setBackgroundStyle(style: Int): AsDialog {
        dialogModel.backgroundStyle = style
        return this
    }


    /**
     * Lambda表达式版本构建
     * @param build [@kotlin.ExtensionFunctionType] Function1<DialogBuild, Unit>
     * @return BottomSheetDialog
     */
    fun build(
        build: DialogBuild.() -> Unit,
    ): BottomSheetDialog {

        DialogBuild.apply(build).apply {

            val config = Config().apply(config!!)
            this@AsDialog.dialogModel.title = config.title
            this@AsDialog.dialogModel.cancelable = config.cancelable
            this@AsDialog.dialogModel.content = config.content
            this@AsDialog.dialogModel.imageIcon = config.imageIcon
            this@AsDialog.dialogModel.imageDrawable = config.imageDrawable
            this@AsDialog.dialogModel.imageBitmap = config.imageBitmap
            this@AsDialog.dialogModel.positiveButton = config.positiveButton
            this@AsDialog.dialogModel.positiveButtonText = config.positiveButtonText
            this@AsDialog.dialogModel.negativeButton = config.negativeButton
            this@AsDialog.dialogModel.negativeButtonText = config.negativeButtonText
            this@AsDialog.dialogModel.neutralButton = config.neutralButton
            this@AsDialog.dialogModel.neutralButtonText = config.neutralButtonText

            this@AsDialog.asBottomSheetCallback = AsDialog.asBottomSheetCallback
            dialogModel.asDialogListener = object : AsDialogListener{
                override fun onClose(asDialog: AsDialog) {
                    asDialogListener?.let { it(asDialog) }
                }

            }
            return this@AsDialog.build()

        }


    }


    /**
     * 构建类
     */
    object DialogBuild {
        var config: (Config.() -> Unit)? = null
        var asBottomSheetCallback: AsBottomSheetCallback = AsDialog.asBottomSheetCallback
        var asDialogListener: ((asDialog: AsDialog) -> Unit)? = null
    }


    /**
     * 构建对话框
     * @return BottomSheetDialog
     */
    fun build(): BottomSheetDialog {

        val binding = AsdialogBottomsheetBinding.inflate(LayoutInflater.from(baseDialog.context))
        dialogModel.asDialogBottomsheetBinding = binding

        val bottomSheetDialog =
            BottomSheetDialog(baseDialog.context, R.style.asdialog_BottomSheetDialog)

        dialogModel.bottomSheetDialog = bottomSheetDialog

        bottomSheetDialog.setContentView(binding.root)

        bottomSheetDialog.setCancelable(dialogModel.cancelable)

        when (dialogModel.backgroundStyle) {
            //上高斯模糊
            GAUSSIAN_BLUR -> {
                dialogModel.blurBackgroundDrawers =
                    FastBlurUtils.getBlurBackgroundDrawer(baseDialog.context as Activity)
                dialogModel.bottomSheetDialog.window?.setBackgroundDrawable(BitmapDrawable(
                    baseDialog.context.resources,
                    dialogModel.blurBackgroundDrawers[4]))
            }
        }

        initDialogBehaviorBinding(binding.asdialogBottomSheetBar,
            baseDialog.context,
            binding.root.parent)

        binding.apply {
            dialogModel.content?.let { asdialogBottomSheetMessage.text = it }
            asdialogBottomSheetTitle.text = dialogModel.title

            //设置点击事件
            dialogModel.positiveButton?.let { it1 ->
                asdialogBottomSheetPositiveButton.setOnClickListener {
                    it1(this@AsDialog)
                }
            }

            dialogModel.negativeButton?.let { it1 ->
                asdialogBottomSheetNegativeButton.setOnClickListener {
                    it1(this@AsDialog)
                }
            }

            dialogModel.neutralButton?.let { it1 ->
                asdialogBottomSheetNeutralButton.setOnClickListener {
                    it1(this@AsDialog)
                }
            }

            //设置按钮文字
            dialogModel.positiveButtonText?.let {
                asdialogBottomSheetPositiveButton.text = it
                asdialogBottomSheetPositiveButton.visibility = View.VISIBLE
            }

            dialogModel.negativeButtonText?.let {
                asdialogBottomSheetNegativeButton.text = it
                asdialogBottomSheetNegativeButton.visibility = View.VISIBLE
            }

            dialogModel.neutralButtonText?.let {
                asdialogBottomSheetNeutralButton.text = it
                asdialogBottomSheetNeutralButton.visibility = View.VISIBLE
            }

            dialogModel.imageDrawable?.let {

                asdialogBottomSheetImage.setImageDrawable(dialogModel.imageDrawable)
                asdialogBottomSheetImage.visibility = View.VISIBLE
            }


            dialogModel.imageBitmap?.let {
                asdialogBottomSheetImage.setImageBitmap(dialogModel.imageBitmap)
                asdialogBottomSheetImage.visibility = View.VISIBLE
            }


            dialogModel.imageIcon?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    asdialogBottomSheetImage.setImageIcon(dialogModel.imageIcon)
                    asdialogBottomSheetImage.visibility = View.VISIBLE
                }
            }



            return bottomSheetDialog


        }


    }

    private fun initDialogBehaviorBinding(
        tipView: View,
        context: Context,
        viewGroup: ViewParent,
    ) {
        //用户行为
        val mDialogBehavior = BottomSheetBehavior.from(viewGroup as View)
        mDialogBehavior.addBottomSheetCallback(object :
            BottomSheetCallback() {
            @SuppressLint("UseCompatLoadingForDrawables", "SwitchIntDef")
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (!asBottomSheetCallback.onStateChanged(bottomSheet, newState)) {
                    //拖动监听
                    val linearLayout: View = tipView
                    when (newState) {
                        BottomSheetBehavior.STATE_DRAGGING -> {
                            linearLayout.background =
                                context.getDrawable(R.color.asdialog_color_primary)
                        }
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            linearLayout.background =
                                context.getDrawable(R.color.asdialog_color_primary_variant)
                        }
                        BottomSheetBehavior.STATE_EXPANDED -> {
                            linearLayout.background =
                                context.getDrawable(R.color.asdialog_color_primary_variant)
                        }

                        BottomSheetBehavior.STATE_HIDDEN -> {
                            dialogModel.asDialogListener?.onClose(this@AsDialog)
                        }
                    }

                    this@AsDialog.onStateChanged(bottomSheet, newState)

                }

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

                asBottomSheetCallback.onSlide(bottomSheet, slideOffset)
                this@AsDialog.onSlide(bottomSheet, slideOffset)

            }

        })
    }

    private fun onSlide(bottomSheet: View, slideOffset: Float) {
        if (dialogModel.blurBackgroundDrawers.isNotEmpty()) {
            when (abs(slideOffset * 100).roundToInt()) {
                in 0..20 -> {
                    dialogModel.bottomSheetDialog.window?.setBackgroundDrawable(BitmapDrawable(
                        baseDialog.context.resources,
                        dialogModel.blurBackgroundDrawers[4]))
                }

                in 20..40 -> {
                    dialogModel.bottomSheetDialog.window?.setBackgroundDrawable(BitmapDrawable(
                        baseDialog.context.resources,
                        dialogModel.blurBackgroundDrawers[3]))
                }

                in 40..60 -> {
                    dialogModel.bottomSheetDialog.window?.setBackgroundDrawable(BitmapDrawable(
                        baseDialog.context.resources,
                        dialogModel.blurBackgroundDrawers[2]))
                }

                in 60..80 -> {
                    dialogModel.bottomSheetDialog.window?.setBackgroundDrawable(BitmapDrawable(
                        baseDialog.context.resources,
                        dialogModel.blurBackgroundDrawers[1]))
                }

                in 80..100 -> {
                    dialogModel.bottomSheetDialog.window?.setBackgroundDrawable(BitmapDrawable(
                        baseDialog.context.resources,
                        dialogModel.blurBackgroundDrawers[0]))
                }
            }
        }
    }

    private fun onStateChanged(bottomSheet: View, newState: Int) {
        if (dialogModel.backgroundStyle == GAUSSIAN_BLUR) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dialogModel.bottomSheetDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            }
        }
    }


}