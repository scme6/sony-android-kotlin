package com.sony.store.myapplication.widget.dialog

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.sony.store.myapplication.R
import kotlinx.android.synthetic.main.dialog_topic.*


/**
 * @Description 提示用
 * @author: qiang
 * @date: 2021-07-01
 */
class TopicDialog(context: Context) : AlertDialog(context) {

    private var mTitle: String = ""
    private var mMessage: String = ""
    private var mPositiveButton: String = ""
    private var mNegativeButton: String = ""
    private var mNegativeOnclickListener: DialogInterface.OnClickListener? = null
    private var mPositiveOnclickListener: DialogInterface.OnClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_topic)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        dialogTitle?.text = mTitle
        message?.text = mMessage
        positive.text = mPositiveButton
        negative.text = mNegativeButton
        message?.movementMethod = LinkMovementMethod.getInstance()
        dialogTitle.isVisible = mTitle.isNotEmpty()
        positive.setOnClickListener {
            mPositiveOnclickListener?.onClick(this, DialogInterface.BUTTON_POSITIVE)
        }
        negative.setOnClickListener {
            mNegativeOnclickListener?.onClick(this, DialogInterface.BUTTON_NEGATIVE)
        }
    }

    override fun setMessage(message2: CharSequence?) {
        super.setMessage(message2)
        this.mMessage = message2.toString()
    }

    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
        this.mTitle = title.toString()
    }

    override fun setButton(
        whichButton: Int,
        text: CharSequence?,
        listener: DialogInterface.OnClickListener?
    ) {
        super.setButton(whichButton, text, listener)
        if (whichButton == DialogInterface.BUTTON_POSITIVE) {
            this.mPositiveButton = text.toString()
            this.mPositiveOnclickListener = listener
        } else if (whichButton == DialogInterface.BUTTON_NEGATIVE) {
            this.mNegativeButton = text.toString()
            this.mNegativeOnclickListener = listener
        }
    }

    override fun show() {
        super.show()
        val manager = this.window!!.windowManager
        val outMetrics = DisplayMetrics()
        manager.defaultDisplay.getMetrics(outMetrics)
        val width = outMetrics.widthPixels
        // val height = outMetrics.heightPixels
        this.window!!.setGravity(Gravity.CENTER)
        this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.window!!.setLayout(
            width - width / 5,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }


}